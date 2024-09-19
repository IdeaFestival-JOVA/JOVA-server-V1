package com.jova.domain.auth.dto.request

import jakarta.validation.constraints.NotBlank

data class SignInRequest(
    @field:NotBlank val code: String
)