//package com.jova.domain.auth.service.Impl;
//
//import com.jova.domain.user.Role;
//import com.jova.global.security.jwt.service.JwtProvider;
//import com.jova.global.security.key.Entity.Key;
//import com.jova.global.security.key.Repository.KeyRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class KeySignInServiceImpl {
//    private final JwtProvider jwtProvider;
//    private final KeyRepository keyRepository;
//
//    public String issueToken (String key) {
//        Key keyEntity = keyRepository.findByKey(key);
//
//        if(keyEntity == null) {
//            throw new IllegalArgumentException("Invalid key");
//        }
//
//        Role role = keyEntity.getRole();
//        return jwtProvider.generateAccessTokenWithKey(key, role);
//    }
//}
