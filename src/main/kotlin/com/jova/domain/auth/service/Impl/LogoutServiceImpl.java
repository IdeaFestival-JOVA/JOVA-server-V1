package com.jova.domain.auth.service.Impl;

import com.jova.domain.auth.entity.Auth;
import com.jova.domain.auth.service.LogoutService;
import com.jova.domain.auth.util.AuthUtil;
import com.jova.global.security.jwt.entity.RefreshToken;
import com.jova.global.security.jwt.exception.ExpiredRefreshTokenException;
import com.jova.global.security.jwt.repository.RefreshRepository;
import com.jova.global.security.jwt.service.BlacklistedTokenService;
import com.jova.global.security.jwt.service.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LogoutServiceImpl implements LogoutService {
    @Autowired
    private final AuthUtil authUtil;
    @Autowired
    private final RefreshRepository refreshRepository;
    @Autowired
    private final BlacklistedTokenService blacklistedTokenService;
    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public void logout(String accessToken) {
        Auth auth = authUtil.getCurrentAser();
        RefreshToken validRefreshToken = refreshRepository.findByUserId(auth.getAuthid());
        if (validRefreshToken == null) {
            throw new ExpiredRefreshTokenException();
        }
        refreshRepository.deleteById(validRefreshToken.getRefreshToken());
        blacklistedTokenService.addTokenToBlacklist(accessToken,jwtProvider.getExpiration(accessToken));
    }
}