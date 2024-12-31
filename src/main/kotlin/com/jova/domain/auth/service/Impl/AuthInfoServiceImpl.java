package com.jova.domain.auth.service.Impl;

import com.jova.domain.auth.entity.Auth;
import com.jova.domain.auth.repository.AuthRepository;
import com.jova.domain.auth.service.AuthInfoService;
import com.jova.global.auth.userdetails.AuthDetails;
import com.jova.global.security.jwt.service.JwtTokenProviderJava;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthInfoServiceImpl implements AuthInfoService {
    private final JwtTokenProviderJava jwtProvider;
    private final AuthRepository authRepository;

    @Override
    public Auth getAuthInfo(String accessToken) {
        if (accessToken.startsWith("Bearer ")) {
            accessToken = accessToken.substring(7);
        }
        Authentication jwtClaims = jwtProvider.getAuthentication(accessToken);
        Object Object = jwtClaims.getPrincipal();
        AuthDetails authDetails = (AuthDetails) Object;
        return authRepository.findByEmail(authDetails.getUsername());
    }

    @Override
    public Auth getUserInfo(UUID uuid) {
        if (authRepository.findByEmail(uuid.toString()) != null) {
            throw new IllegalArgumentException("존재하지 않는 유저입니다.");
        }
        return authRepository.findAuthByAuthid(uuid);
    }
}