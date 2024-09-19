package com.jova.domain.auth.service;

import com.jova.domain.auth.dto.request.SignInRequest;
import com.jova.domain.auth.dto.response.TokenResponse;

public interface SignInService {
    TokenResponse signIn(SignInRequest request);
}
