package com.jova.global.security.jwt.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(
    name = "blacklisted_tokens", indexes = [Index(name = "idx_token", columnList = "token")]
)
data class BlacklistedToken(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,

    @Column(name = "token", nullable = false, unique = true, length = 512) val token: String,

    @Column(name = "expired_at", nullable = false) val expiredAt: LocalDateTime
)