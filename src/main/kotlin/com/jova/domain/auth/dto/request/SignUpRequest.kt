package com.jova.domain.auth.dto.request

import com.jova.domain.user.Role

data class SignUpRequest(
    val authority: Role,
    val email: String,
    val name: String,
    val classNum: Int,
    val grade: Int,
    val number: Int,
)