package com.jova.domain.article.controller;

import com.jova.domain.article.dto.request.ArticleRequestDTO;
import com.jova.domain.article.entity.Article;
import com.jova.domain.article.service.impl.ArticleServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="ArticleAPI", description = "게시글 관리 API")
@RestController
@RequestMapping("/articles")

public class ArticleController {

    private final ArticleServiceImpl articleServiceImpl;

    public ArticleController(ArticleServiceImpl articleServiceImpl) {
        this.articleServiceImpl = articleServiceImpl;
    }

    @Operation(summary = "게시글 전체 조회", description = "게시글을 전체 조회하는 API")
    @GetMapping("/list")
    public List<Article> getAllArticles(){
        return articleServiceImpl.findAll();
    }

    @Operation(summary = "특정 게시글 조회", description = "특정 게시글을 조회하는 API")
    @GetMapping("/{id}")
    public Article getArticleById(@PathVariable @NotNull Long id){
        return articleServiceImpl.findArticleById(id);
    }

    @Operation(summary = "게시글 생성", description = "게시글을 생성하는 API")
    @PostMapping("/")
    public Article createArticle(@RequestBody @NotBlank ArticleRequestDTO articleRequestDTO){
        return articleServiceImpl.saveArticle(articleRequestDTO.toEntity());
    }

    @Operation(summary = "게시글 수정", description = "게시글을 수정하는 API")
    @PutMapping("/{id}/update")
    public Article updateArticle(@PathVariable Long id, @RequestBody Article article){
        @NotBlank
        Article existedarticle = articleServiceImpl.findArticleById(id);
        existedarticle.setTitle(article.getTitle());
        existedarticle.setContent(article.getContent());
        existedarticle.setCategory(article.getCategory());
        return articleServiceImpl.saveArticle(existedarticle);
    }

    @Operation(summary = "게시글 삭제", description = "게시글을 삭제하는 API")
    @DeleteMapping("/{id}/delete")
    public void deleteArticleById(@PathVariable @NotNull Long id){
        articleServiceImpl.deleteArticleById(id);
    }

}
