package com.jova.domain.auth.dto.request

data class SignInRequest(
    val email: String,
    val password: String,
)