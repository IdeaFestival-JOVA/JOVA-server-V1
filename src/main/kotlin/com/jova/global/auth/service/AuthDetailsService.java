package com.jova.global.auth.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthDetailsService {
    public UserDetails loadUserByUsername(String id);
}
