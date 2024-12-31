package com.jova.domain.auth.dto.request

data class SignUpRequest(
    val email: String,
    val name: String,
    val classNum: Int,
    val grade: Int,
    val number: Int,
    val password: String,
)