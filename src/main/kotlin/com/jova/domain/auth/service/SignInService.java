package com.jova.domain.auth.service;

import com.jova.domain.auth.dto.request.SignInRequest;
import com.jova.domain.auth.dto.response.tokenResponse;

public interface SignInService {
    tokenResponse signIn(SignInRequest request);
}
