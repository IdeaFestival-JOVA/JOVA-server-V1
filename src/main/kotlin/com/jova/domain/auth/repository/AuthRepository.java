package com.jova.domain.auth.repository;

import com.jova.domain.auth.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Auth, Long> {
    Auth findByname(String username);

    Auth findByEmail(String email);

    Auth findBynameOrEmail(String username, String email);

    Boolean existsByname(String username);

    Boolean existsByEmail(String email);
}