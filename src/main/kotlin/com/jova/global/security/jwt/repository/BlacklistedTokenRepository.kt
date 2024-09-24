package com.jova.global.security.jwt.repository

import com.jova.global.security.jwt.entity.BlacklistedToken
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.*

@Repository
interface BlacklistedTokenRepository : JpaRepository<BlacklistedToken, Long> {
    fun findByToken(token: String): Optional<BlacklistedToken>
    fun deleteByExpiredAtBefore(time: LocalDateTime)
}