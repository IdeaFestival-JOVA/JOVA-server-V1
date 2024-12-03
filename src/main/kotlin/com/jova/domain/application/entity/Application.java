package com.jova.domain.application.entity;

import com.jova.domain.article.entity.Article;
import com.jova.domain.auth.entity.Auth;
import com.jova.domain.user.UserMajor;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name="application")
@NoArgsConstructor
@Getter
public class Application {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Article article;

    @Column(name="유저전공",nullable = false, unique = true)
    private UserMajor major;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="authid", nullable=false)
    private Auth auth;

//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name="user_major")
//    private UserMajor userMajor;

    @Column(name="applied_time", nullable=false)
    private LocalDateTime appliedAt;

    @Builder
    public Application(Article article, LocalDateTime appliedAt, Auth auth) {
        this.article = article;
        this.appliedAt = appliedAt != null ? appliedAt : LocalDateTime.now();
        this.auth = auth;
    }

}
