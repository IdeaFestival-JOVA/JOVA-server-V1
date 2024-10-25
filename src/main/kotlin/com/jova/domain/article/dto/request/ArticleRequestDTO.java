package com.jova.domain.article.dto.request;

import com.jova.domain.article.entity.Article;
import lombok.Builder;
import java.time.LocalDateTime;

@Builder
public class ArticleRequestDTO {
    private String title;
    private String content;
    private String category;
    private LocalDateTime createdAt;

    @Builder
    public ArticleRequestDTO(String title, String content, String category, LocalDateTime createdAt) {
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
