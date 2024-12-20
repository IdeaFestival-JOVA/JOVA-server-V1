package com.jova.domain.article.entity;

import com.jova.domain.user.UserMajor;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="article")
@Getter
@Setter
@NoArgsConstructor

public class Article {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long article_id;
    @Column(nullable = false, name = "article_title")
    private String title;
    @Column(nullable = false, name = "article_content")
    private String content;
    @Column(nullable = false, name = "article_category")
    private UserMajor category;
    @Column(nullable = false, name = "article_time")
    private String createdAt;

    @Builder
    public Article(String title, String content, UserMajor category, String createdAt) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.createdAt = createdAt;
    }
}

