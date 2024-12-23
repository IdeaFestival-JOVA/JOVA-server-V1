package com.jova.domain.user.Service;

import com.jova.domain.auth.dto.response.TokenResponse;
import com.jova.domain.user.Entity.User;
import com.jova.domain.user.Repository.UserRepository;
import com.jova.domain.user.Service.impl.UserService;
import com.jova.global.security.jwt.service.JwtTokenProviderJava;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProviderJava jwtTokenProvider;

    @Override
    public Optional<User> findUserById(Long id){
        return userRepository.findById(id);
    }
}
