package com.jova.domain.user.Entity;

import com.jova.domain.user.UserMajor;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(nullable = false, name="username")
    private String username;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name="user_majors", joinColumns = @JoinColumn(name="user_id"))
    @Column(name="user_major")
    @Enumerated(EnumType.STRING)
    private List<UserMajor> major;

    @Builder
    public User(String username, List<UserMajor> major) {
        this.username = username;
        this.major = major;
    }

}
