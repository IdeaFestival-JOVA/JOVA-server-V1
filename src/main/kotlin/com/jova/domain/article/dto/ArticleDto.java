package com.jova.domain.article.dto;

import com.jova.domain.article.entity.Article;
import com.jova.domain.user.UserMajor;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {
    private Long article_id;
    private String title;
    private String content;
    private UserMajor category;
    private LocalDateTime createdAt;
    private String author;

    @Builder
    public ArticleDto(String title, String content, UserMajor category, String author) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.author = author;
    }

    public Article toEntity(Long article_id, String title, String content, UserMajor category, String author) {
        return Article.builder()
                .article_id(article_id)
                .title(title)
                .content(content)
                .category(category)
                .author(author)
                .createdAt(createdAt)
                .build();
    }
}
