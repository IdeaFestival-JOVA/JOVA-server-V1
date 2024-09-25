package com.jova.domain.auth.service;

import com.jova.domain.auth.entity.Auth;

public interface AuthInfoService {
    Auth getAuthInfo(String accessToken);
}