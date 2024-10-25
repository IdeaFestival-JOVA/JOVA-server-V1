package com.jova.domain.article.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor

public class ArticleEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name="article_title")
    private String title;
    @Column(nullable = false, name="article_content")
    private String content;
    @Column(nullable = false, name="article_category")
    private String category;
    @Column(nullable = false, name="article_time")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder
    public ArticleEntity(String title, String content, String category, LocalDateTime createdAt) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
    }
}

