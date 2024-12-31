package com.jova.global.security.jwt.service

import com.jova.domain.auth.dto.response.TokenResponse
import com.jova.domain.auth.enums.Authority
import com.jova.domain.auth.repository.AuthRepository
import com.jova.domain.user.Role
import com.jova.global.auth.service.AuthDetailsService
import com.jova.global.exception.ErrorCode
import com.jova.global.exception.JovaException
import com.jova.global.security.jwt.exception.ExpiredTokenException
import com.jova.global.security.jwt.exception.InvalidTokenException
import com.jova.global.security.key.Repository.KeyRepository
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import jakarta.annotation.PostConstruct
import jakarta.servlet.http.HttpServletRequest
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import java.security.InvalidKeyException
import java.security.Key
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Component
@Slf4j
class JwtProvider(
    private val authDetailsService: AuthDetailsService,
    private val blacklistedTokenService: BlacklistedTokenService,
    private val keyRepository: KeyRepository,
    private val authRepository: AuthRepository
) {
    @Value("\${jwt.secret}")
    private lateinit var secretKey: String
    private lateinit var key: Key

    companion object {
        private const val AUTHORITIES_KEY = "auth"
        private const val BEARER_TYPE = "Bearer "
        private const val ACCESS_TOKEN_TIME = 60L * 60 * 24 * 1000
        private const val REFRESH_TOKEN_TIME = 60L * 60 * 24 * 7 * 1000
        private const val AUTHORIZATION_HEADER = "Authorization"
        private const val BEARER_PREFIX = "Bearer "
    }

    @PostConstruct
    fun init() {
        val keyBytes = Decoders.BASE64.decode(secretKey)
        key = Keys.hmacShaKeyFor(keyBytes)
    }

    fun generateTokenDto(id: UUID,role:Authority): TokenResponse {
        val accessToken = generateAccessToken(id,role)
        val refreshToken = generateRefreshToken(id)
        val accessTokenExpiresIn = LocalDateTime.now().plusSeconds(ACCESS_TOKEN_TIME / 1000)
        val refreshTokenExpiresIn = LocalDateTime.now().plusSeconds(REFRESH_TOKEN_TIME / 1000)
        val tokenResponse = TokenResponse.builder().build()
        tokenResponse.accessTokenExpiration = accessTokenExpiresIn
        tokenResponse.refreshTokenExpiration = refreshTokenExpiresIn
        tokenResponse.accessToken = accessToken
        tokenResponse.refreshToken = refreshToken
        return tokenResponse
    }

    fun getExpiration(accessToken: String): LocalDateTime {
        val claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).body
        val expirationTime = claims.expiration.time
        return Instant.ofEpochMilli(expirationTime).atZone(ZoneId.systemDefault()).toLocalDateTime()
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
        claims[AUTHORITIES_KEY] ?: throw JovaException(ErrorCode.INVALID_TOKEN)
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

    fun generateAccessToken(id: UUID,role:Authority): String {
        val now = Date().time
        val accessTokenExpiresIn = Date(now + ACCESS_TOKEN_TIME)
        return Jwts.builder().setSubject(id.toString()).claim(AUTHORITIES_KEY, role).setIssuedAt(Date())
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
        val expiration = claims.expiration.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()

        blacklistedTokenService.addTokenToBlacklist(token, expiration)
    }

    fun generateAccessTokenWithKey(key: Key, role: Role): String {
        val now = Date().time
        val expiration = Date(now + ACCESS_TOKEN_TIME)

        return Jwts.builder()
            .setSubject("ef80298f-c50b-4da3-81a6-1da86d398a29")
            .claim(AUTHORITIES_KEY, role)
            .setIssuedAt(Date())
            .setExpiration(expiration)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    fun issueTokenIfKeyMatches(keyInput: String): String {

        if (keyInput.isEmpty()) {
            throw InvalidKeyException("Key Input cannot be empty")
        }

        println("Received key Input: $keyInput")
        if(!keyRepository.existsByKey(keyInput)) {
            println("Key not found in repository for input: $keyInput")
            throw InvalidKeyException("Invalid key input")
        }
        return generateAccessTokenWithKey(key, Role.ROLE_ADMIN)
    }
}