package com.jova.domain.auth.repository;

import com.jova.domain.auth.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthRepository extends JpaRepository<Auth, UUID> {

    Auth findByEmail(String email);

    Boolean existsByName(String name);

    Boolean existsByEmail(String email);

    Auth findAuthByAuthid(UUID authId);

}