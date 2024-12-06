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
    private LocalDateTime createdAt;

    @Builder
    public ArticleRequestDTO(String title, String content, UserMajor category, LocalDateTime createdAt) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.createdAt = createdAt;
    }

    public Article toEntity(){
            return Article.builder()
                    .title(title)
                    .content(content)
                    .category(category)
                    .createdAt(createdAt)
                    .build();
    }
}
