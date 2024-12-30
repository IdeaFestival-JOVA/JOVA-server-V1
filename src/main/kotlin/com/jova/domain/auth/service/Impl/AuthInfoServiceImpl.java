package com.jova.domain.auth.service.Impl;

import com.jova.domain.auth.dto.response.TokenResponse;
import com.jova.domain.auth.entity.Auth;
import com.jova.domain.auth.repository.AuthRepository;
import com.jova.domain.auth.service.AuthInfoService;
import com.jova.global.auth.userdetails.AuthDetails;
import com.jova.global.security.jwt.service.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthInfoServiceImpl implements AuthInfoService {
    private final JwtProvider jwtProvider;
    private final AuthRepository authRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

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
        if (authRepository.findAuthByAuthid(uuid) != null) {
            throw new IllegalArgumentException("존재하지 않는 유저입니다.");
        }
        return authRepository.findAuthByAuthid(uuid);
    }
    @Override
    public String signIn(String email, String password) {
//        if (!authRepository.existsByEmail(email)) {
//            throw new IllegalArgumentException("이메일 또는 비밀번호가 일치하지 않습니다.");
//        }
//
//        Auth auth = authRepository.findByEmail(email);
//
//        if (!auth.getPassword().equals(passwordEncoder.encode(password))) {
//            throw new IllegalArgumentException("이메일 또는 비밀번호가 일치하지 않습니다.");
//        }
//
//        TokenResponse tokenResponse = jwtProvider.generateTokenDto(auth.getAuthid(), auth.getAuthority());
//        String accessToken = tokenResponse.getAccessToken();
//        return getAuthInfo(accessToken);
//    }
        Auth auth = authRepository.findByEmail(email);
        if (auth == null) {
            throw new IllegalArgumentException("이메일 불일치");
        }
        if (auth.getPassword().equals(passwordEncoder.encode(password))) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        TokenResponse tokenDto = jwtProvider.generateTokenDto(auth.getAuthid(), auth.getAuthority());
        return tokenDto.getAccessToken();
    }
}