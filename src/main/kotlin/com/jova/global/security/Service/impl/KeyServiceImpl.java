package com.jova.global.security.Service.impl;

import com.jova.global.security.key.Repository.KeyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KeyServiceImpl {
    private final KeyRepository keyRepository;

    public boolean isExists (String key) {

    }
}
