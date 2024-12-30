package com.jova.domain.user.Service.impl;

import com.jova.domain.user.Entity.User;
import com.jova.domain.user.Repository.UserRepository;
import com.jova.domain.user.Service.UserService;
import com.jova.global.security.jwt.service.JwtTokenProviderJava;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> findUserById(Long id){
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findUserByName(String username){
        return userRepository.findUserByName(username);
    }

    @Override
    public List<User> findUserAll() {return userRepository.findAll(); }
}
