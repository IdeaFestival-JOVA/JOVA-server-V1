package com.jova.domain.article.dto.request;

import com.jova.domain.article.entity.Article;
import com.jova.domain.user.UserMajor;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class ArticleRequestDTO {
    private String title;
    private String content;
    private UserMajor category;
    private String author;
    private LocalDateTime endsAt;

    @Builder
    public ArticleRequestDTO(String title, String content, UserMajor category, String author, LocalDateTime endsAt) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.author = author;
        this.endsAt = endsAt;
    }

    public Article toEntity(){
            return Article.builder()
                    .title(title)
                    .content(content)
                    .category(category)
                    .author(author)
                    .endsAt(endsAt)
                    .build();
    }
}
