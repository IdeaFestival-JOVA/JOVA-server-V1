package com.jova.domain.application.entity;

import com.jova.domain.article.entity.ArticleEntity;
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
    private ArticleEntity articleEntity;
/*
    private String applicant;
    private String applicant_category;
    private String applicant_gradeandclass;*/

    @Column(name="applied_time", nullable=false)
    private LocalDateTime appliedAt;

    @Builder
    public Application(ArticleEntity articleEntity, LocalDateTime appliedAt) {
        this.articleEntity = articleEntity;
        this.appliedAt = appliedAt != null ? appliedAt : LocalDateTime.now();
    }
}
