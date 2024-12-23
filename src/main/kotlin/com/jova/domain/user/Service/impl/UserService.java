package com.jova.domain.user.Service.impl;

import com.jova.domain.user.Entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findUserById(Long id);
    Optional<User> findUserByName(String name);
}
