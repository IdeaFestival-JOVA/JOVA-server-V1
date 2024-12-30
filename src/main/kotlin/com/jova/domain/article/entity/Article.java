package com.jova.domain.article.entity;

import com.jova.domain.user.UserMajor;
import jakarta.persistence.*;
import lombok.*;

@Entity(name="article")
@Getter
@Builder
@AllArgsConstructor
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
    @Column(nullable = false, name = "article_created_time")
    private String createdAt;
    @Column(nullable = false, name = "aritlce_author")
    private String author;

    @Builder
    public Article(String title, String content, UserMajor category, String author) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.author = author;
    }
}

