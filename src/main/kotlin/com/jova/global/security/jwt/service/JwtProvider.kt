package com.jova.global.security.jwt.provider

import com.jova.domain.auth.dto.response.TokenResponse
import com.jova.global.auth.service.AuthDetailsService
import com.jova.global.exception.ErrorCode
import com.jova.global.exception.JovaException
import com.jova.global.security.jwt.exception.ExpiredTokenException
import com.jova.global.security.jwt.exception.InvalidTokenException
import com.jova.global.security.jwt.service.BlacklistedTokenService
import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import jakarta.annotation.PostConstruct
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import java.security.Key
import java.time.LocalDateTime
import java.util.*

@Component
class JwtProvider(
    private val authDetailsService: AuthDetailsService,
    private val blacklistedTokenService: BlacklistedTokenService
) {
    @Value("\${jwt.secret}")
    private lateinit var secretKey: String
    private lateinit var key: Key

    companion object {
        private const val AUTHORITIES_KEY = "auth"
        private const val BEARER_TYPE = "Bearer "
        private const val ACCESS_TOKEN_TIME = 60L * 60 * 24 * 1000
        private const val REFRESH_TOKEN_TIME = 60L * 60 * 24 * 30 * 1000
        private const val AUTHORIZATION_HEADER = "Authorization"
        private const val BEARER_PREFIX = "Bearer "
    }

    @PostConstruct
    fun init() {
        val keyBytes = Decoders.BASE64.decode(secretKey)
        key = Keys.hmacShaKeyFor(keyBytes)
    }

    fun generateTokenDto(id: UUID): TokenResponse {
        val accessToken = generateAccessToken(id)
        val refreshToken = generateRefreshToken(id)
        val accessTokenExpiresIn = LocalDateTime.now().plusSeconds(ACCESS_TOKEN_TIME / 1000)
        val refreshTokenExpiresIn = LocalDateTime.now().plusSeconds(REFRESH_TOKEN_TIME / 1000)
        val tokenResponse = TokenResponse(
            accessToken = accessToken,
            refreshToken = refreshToken,
            accessTokenExpiration = accessTokenExpiresIn,
            refreshTokenExpiration = refreshTokenExpiresIn
        )
        return tokenResponse
    }

    fun getExpiration(accessToken: String): Long {
        val claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).body
        return claims.expiration.time
    }

    fun validateToken(token: String): Boolean {
        return try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
            !blacklistedTokenService.isTokenBlacklisted(token)
        } catch (e: ExpiredJwtException) {
            throw ExpiredTokenException()
        } catch (e: Exception) {
            throw InvalidTokenException()
        }
    }

    fun getAuthentication(accessToken: String): Authentication {
        val claims = parseClaims(accessToken)

        if (claims[AUTHORITIES_KEY] == null) {
            throw JovaException(ErrorCode.INVALID_TOKEN)
        }

        val principal: UserDetails = authDetailsService.loadUserByUsername(claims.subject)
        return UsernamePasswordAuthenticationToken(principal, "", principal.authorities)
    }

    private fun parseClaims(accessToken: String): Claims {
        return try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).body
        } catch (e: ExpiredJwtException) {
            e.claims
        }
    }

    fun resolveToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader(AUTHORIZATION_HEADER)
        return if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            bearerToken.substring(BEARER_PREFIX.length)
        } else null
    }

    fun parseRefreshToken(refreshToken: String): String? {
        return if (refreshToken.startsWith(BEARER_TYPE)) {
            refreshToken.replace(BEARER_TYPE, "")
        } else null
    }

    fun generateAccessToken(id: UUID): String {
        val now = Date().time
        val accessTokenExpiresIn = Date(now + ACCESS_TOKEN_TIME)
        return Jwts.builder().setSubject(id.toString()).claim(AUTHORITIES_KEY, "JWT").setIssuedAt(Date())
            .setExpiration(accessTokenExpiresIn).signWith(key, SignatureAlgorithm.HS256).compact()
    }

    fun generateRefreshToken(id: UUID): String {
        val now = Date().time
        val refreshTokenExpiresIn = Date(now + REFRESH_TOKEN_TIME)

        return Jwts.builder().setSubject(id.toString()).setExpiration(refreshTokenExpiresIn)
            .signWith(key, SignatureAlgorithm.HS256).compact()
    }

    fun blacklistToken(token: String) {
        val claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).body
        val expiration = claims.expiration.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime()

        blacklistedTokenService.addTokenToBlacklist(token, expiration)
    }
}