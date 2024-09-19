package com.jova.domain.auth.util

import org.springframework.stereotype.Component
import org.springframework.security.core.context.SecurityContextHolder
import com.jova.domain.auth.entity.Auth
import com.jova.domain.auth.exception.AuthNotFoundException
import com.jova.domain.auth.repository.AuthRepository

@Component
class AuthUtil(private val authRepository: AuthRepository) {
    fun getCurrentAser(): Auth {
        val email: String = SecurityContextHolder.getContext().authentication.name
        return authRepository.findByEmail(email) ?: throw AuthNotFoundException()
    }
}