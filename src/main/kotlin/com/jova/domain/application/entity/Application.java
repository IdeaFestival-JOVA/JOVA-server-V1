package com.jova.domain.application.entity;

import com.jova.domain.article.entity.Article;
import com.jova.domain.auth.entity.Auth;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class Application {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="article_id", nullable=false)
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="authid", nullable=false)
    private Auth auth;

    @Column(name="applied_time", nullable=false)
    private LocalDateTime appliedAt;

    @Builder
    public Application(Article article, LocalDateTime appliedAt, Auth auth) {
        this.article = article;
        this.appliedAt = appliedAt != null ? appliedAt : LocalDateTime.now();
        this.auth = auth;
    }
}
