package com.jova.global.security.jwt.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(
    name = "refresh_tokens",
    indexes = [Index(name = "idx_user_id", columnList = "user_id")]
)
data class RefreshToken(
    @Id
    @Column(name = "refresh_token", nullable = false, unique = true)
    var refreshToken: String,

    @Column(name = "user_id", nullable = false)
    var userId: UUID,

    @Column(name = "expired_at", nullable = false)
    var expiredAt: LocalDateTime
)