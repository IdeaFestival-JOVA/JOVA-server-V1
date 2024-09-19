package com.jova.global.security.jwt

import org.springframework.stereotype.Component

@Component
class JwtProvider(private val authDetailsService: AuthDeta) {
}