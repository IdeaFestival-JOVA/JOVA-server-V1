package com.jova.domain.auth.service;

import com.jova.domain.auth.entity.Auth;

import java.util.UUID;

public interface AuthInfoService {
    Auth getAuthInfo(String accessToken);
    Auth getUserInfo(UUID authId);
}