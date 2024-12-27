package com.jova.domain.article.dto.request;

import com.jova.domain.article.entity.Article;
import com.jova.domain.user.UserMajor;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class ArticleRequestDTO {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotBlank
    private UserMajor category;
    @NotBlank
    private String author;
    private String endsAt;

    @Builder
    public ArticleRequestDTO(String title, String content, UserMajor category, String author, String endsAt) {
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
