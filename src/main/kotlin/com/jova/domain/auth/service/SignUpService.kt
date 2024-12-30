package com.jova.domain.auth.service

import com.jova.domain.auth.dto.request.SignUpRequest
import com.jova.domain.auth.entity.Auth

interface SignUpService {
    fun signUp(signUpRequest: SignUpRequest): Auth
}