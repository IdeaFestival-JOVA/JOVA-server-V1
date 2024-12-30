package com.jova.global.security.key.component;

import com.jova.global.security.key.Repository.KeyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Transactional
public class VerifyKey {
    private final KeyRepository keyRepository;

    public boolean verifyKey(String key) {
        return keyRepository.existsByKey(key);
    }
}
