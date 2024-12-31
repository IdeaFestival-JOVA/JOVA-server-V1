package com.jova.domain.auth.service.Impl

import com.jova.domain.auth.dto.request.SignUpRequest
import com.jova.domain.auth.entity.Auth
import com.jova.domain.auth.enums.Authority
import com.jova.domain.auth.exception.AuthAlreayExisted
import com.jova.domain.auth.repository.AuthRepository
import com.jova.domain.auth.service.SignUpService
import com.jova.domain.auth.vo.StudentNum
import org.springframework.stereotype.Service
import java.util.*

@Service
class SignUpServiceImpl(
    private val authRepository: AuthRepository
): SignUpService {
    override fun signUp(signUpRequest: SignUpRequest): Auth {

        if (authRepository.existsByEmail(signUpRequest.email)) {
            throw Exception("Email already in use")
        }

        return authRepository.save(Auth(
            authid = UUID.randomUUID(),
            email = signUpRequest.email,
            name = signUpRequest.name,
            studentNum = StudentNum(grade = signUpRequest.grade, classNum = signUpRequest.classNum, number = signUpRequest.number),
            authority = Authority.ROLE_ADMIN,
            password = signUpRequest.password
        ))
    }
}