package com.jova.global.security.jwt.service

import com.jova.global.security.jwt.entity.BlacklistedToken
import com.jova.global.security.jwt.repository.BlacklistedTokenRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class BlacklistedTokenService(
    private val blacklistedTokenRepository: BlacklistedTokenRepository
) {
    @Transactional
    fun addTokenToBlacklist(token: String, expiredAt: LocalDateTime) {
        val blacklistedToken = BlacklistedToken(
            token = token, expiredAt = expiredAt
        )
        blacklistedTokenRepository.save(blacklistedToken)
    }

    fun isTokenBlacklisted(token: String): Boolean {
        return blacklistedTokenRepository.findByToken(token).isPresent
    }

    @Transactional
    fun removeExpiredTokens(currentTime: LocalDateTime) {
        blacklistedTokenRepository.deleteByExpiredAtBefore(currentTime)
    }
}