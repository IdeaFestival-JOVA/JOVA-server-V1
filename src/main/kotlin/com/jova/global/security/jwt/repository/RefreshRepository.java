package com.jova.global.security.jwt.repository;

import com.jova.global.security.jwt.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshRepository extends JpaRepository<RefreshToken, String> {
    void deleteByRefreshToken(String refreshToken);

    RefreshToken findByRefreshToken(String refreshToken);
}