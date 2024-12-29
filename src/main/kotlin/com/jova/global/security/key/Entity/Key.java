package com.jova.global.security.key.Entity;

import com.jova.domain.user.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="key_table")
public class Key {
    @Id @Column(name="`key`", unique=true)
    private String key;

    @Column(name="role", nullable=false)
    @Enumerated(EnumType.STRING)
    private Role role;
}
