package com.jova.global.security.key.Entity;

import com.jova.domain.auth.enums.Authority;
import jakarta.persistence.*;

import javax.management.relation.Role;

@Entity
public class Key {
    @Id @Column(name="key", unique=true)
    private String key;

    @Column(name="role", nullable=false)
    @Enumerated(EnumType.STRING)
    private Authority authority;
}
