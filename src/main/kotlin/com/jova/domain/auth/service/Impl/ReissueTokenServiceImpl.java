package com.jova.domain.auth.service.Impl;

import com.jova.domain.auth.dto.response.TokenResponse;
import com.jova.domain.auth.entity.Auth;
import com.jova.domain.auth.exception.AuthNotFoundException;
import com.jova.domain.auth.repository.AuthRepository;
import com.jova.domain.auth.service.ReissueTokenService;
import com.jova.global.security.jwt.entity.RefreshToken;
import com.jova.global.security.jwt.exception.ExpiredRefreshTokenException;
import com.jova.global.security.jwt.repository.RefreshRepository;
import com.jova.global.security.jwt.service.JwtProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class ReissueTokenServiceImpl implements ReissueTokenService {
    private final JwtProvider jwtProvider;
    private final RefreshRepository refreshRepository;
    private final AuthRepository authRepository;

    @Transactional
    public TokenResponse reissueToken(String refreshToken) {
        String parseRefreshToken = jwtProvider.parseRefreshToken(refreshToken);
        RefreshToken refreshEntity = refreshRepository.findById(Objects.requireNonNull(parseRefreshToken)).orElseThrow(ExpiredRefreshTokenException::new);
        Auth auth = authRepository.findById(refreshEntity.getUserId()).orElseThrow(AuthNotFoundException::new);
        TokenResponse tokenResponse = jwtProvider.generateTokenDto(Objects.requireNonNull(auth.getId()));
        RefreshToken token = new RefreshToken(tokenResponse.getRefreshToken(), auth.getId(), tokenResponse.getRefreshTokenExpiration());
        refreshRepository.save(token);
        return tokenResponse;
    }
}