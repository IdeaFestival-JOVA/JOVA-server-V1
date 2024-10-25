package com.jova.domain.article.controller;

import com.jova.domain.article.dto.request.ArticleRequestDTO;
import com.jova.domain.article.entity.ArticleEntity;
import com.jova.domain.article.service.ArticleService;
import jakarta.validation.constraints.NotBlank;
import org.apache.tomcat.util.digester.SystemPropertySource;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.List;

@RestController
@RequestMapping("/articles")

public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    //게시글 전체 조회
    @GetMapping()
    public List<ArticleEntity> getAllArticles(){
        return articleService.findAll();
    }

    //특정 게시글 조회
    @GetMapping("/{id}")
    public ArticleEntity getArticleById(@PathVariable @NotNull Long id){
        return articleService.findArticleById(id);
    }

    //게시글 생성
    @PostMapping
    public ArticleEntity createArticle(@RequestBody @NotBlank ArticleRequestDTO articleRequestDTO){
        return articleService.saveArticle(articleRequestDTO.toEntity());
    }

    //게시글 수정
    @PutMapping("/{id}")
    public ArticleEntity updateArticle(@PathVariable Long id, @RequestBody ArticleEntity article){
        @NotBlank
        ArticleEntity existedarticleEntity = articleService.findArticleById(id);
        existedarticleEntity.setTitle(article.getTitle());
        existedarticleEntity.setContent(article.getContent());
        existedarticleEntity.setCategory(article.getCategory());
        return articleService.saveArticle(existedarticleEntity);
    }

    //게시글 삭제
    @DeleteMapping("/{id}")
    public void deleteArticleById(@PathVariable @NotNull Long id){
        articleService.deleteArticleById(id);
    }



}
