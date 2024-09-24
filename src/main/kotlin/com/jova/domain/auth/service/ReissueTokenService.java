package com.jova.domain.auth.service;

import com.jova.domain.auth.dto.response.TokenResponse;

public interface ReissueTokenService {
    public TokenResponse reissueToken(String refreshToken);
}