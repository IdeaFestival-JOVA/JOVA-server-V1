package com.jova.global.auth.service.Impl;

import com.jova.domain.auth.entity.Auth;
import com.jova.domain.auth.repository.AuthRepository;
import com.jova.global.auth.service.AuthDetailsService;
import com.jova.global.auth.userdetails.AuthDetails;
import com.jova.global.exception.ErrorCode;
import com.jova.global.exception.JovaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthDetailsServiceImpl implements AuthDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(AuthDetailsService.class);
    private final AuthRepository authRepository;

    private AuthDetailsServiceImpl(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String id) {
        logger.info("Loading User by ID: {}", id);
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            logger.error("Invalid UUID format for ID: {}", id);
            throw new JovaException(ErrorCode.USER_NOT_FOUND);
        }
        Auth auth = authRepository.findById(uuid).orElseThrow(() -> {
            logger.error("User not found for ID: {}", id);
            return new JovaException(ErrorCode.USER_NOT_FOUND);
        });
        logger.info("User found for ID: {}", id);
        return new AuthDetails(auth);
    }
}