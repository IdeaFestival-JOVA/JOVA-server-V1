package com.jova.global.security.key.component;

import com.jova.domain.user.Role;
import com.jova.global.security.key.Repository.KeyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Transactional
@RequiredArgsConstructor
public class GetKeyOfRole {
    private final KeyRepository keyRepository;

    public Role getKeyOfRole(String key) {
        return keyRepository.findByKey(key).getRole();
    }
}
