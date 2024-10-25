package com.jova.domain.article.controller;

import com.jova.domain.article.dto.request.ArticleRequestDTO;
import com.jova.domain.article.entity.Article;
import com.jova.domain.article.service.ArticleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="ArticleAPI", description = "게시글 관리 API")
@RestController
@RequestMapping("/articles")

public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    //게시글 전체 조회
    @GetMapping()
    public List<Article> getAllArticles(){
        return articleService.findAll();
    }

    //특정 게시글 조회
    @GetMapping("/{id}")
    public Article getArticleById(@PathVariable @NotNull Long id){
        return articleService.findArticleById(id);
    }

    //게시글 생성
    @PostMapping
    public Article createArticle(@RequestBody @NotBlank ArticleRequestDTO articleRequestDTO){
        return articleService.saveArticle(articleRequestDTO.toEntity());
    }

    //게시글 수정
    @PutMapping("/{id}")
    public Article updateArticle(@PathVariable Long id, @RequestBody Article article){
        @NotBlank
        Article existedarticle = articleService.findArticleById(id);
        existedarticle.setTitle(article.getTitle());
        existedarticle.setContent(article.getContent());
        existedarticle.setCategory(article.getCategory());
        return articleService.saveArticle(existedarticle);
    }

    //게시글 삭제
    @DeleteMapping("/{id}")
    public void deleteArticleById(@PathVariable @NotNull Long id){
        articleService.deleteArticleById(id);
    }

}
