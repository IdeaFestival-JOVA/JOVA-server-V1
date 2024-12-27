package com.jova.domain.article.controller;

import com.jova.domain.article.dto.request.ArticleRequestDTO;
import com.jova.domain.article.entity.Article;
import com.jova.domain.article.service.ArticleService;
import com.jova.domain.auth.dto.response.TokenResponse;
import com.jova.global.security.jwt.service.JwtProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="ArticleAPI", description = "게시글 관리 API")
@Slf4j
@RestController
@RequestMapping("/articles")
public class ArticleController {

    public static final Logger logger = LoggerFactory.getLogger(ArticleController.class);
    private ArticleService articleService;
    private JwtProvider jwtProvider;
    private TokenResponse tokenResponse;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Operation(summary = "게시글 전체 조회", description = "게시글을 전체 조회하는 API")
    @GetMapping("/list")

    public List<Article> getAllArticles(){
        logger.info("전체 게시글을 조회합니다.");
        logger.error("게시글 조회에 실패했습니다.");
        return articleService.findAll(); }

    @Operation(summary = "특정 게시글 조회", description = "특정 게시글을 조회하는 API")
    @GetMapping("/{id}")
    public Article getArticleById(@PathVariable @NotNull Long id){
        return articleService.findArticleById(id);
    }

    @Operation(summary = "게시글 생성", description = "게시글을 생성하는 API")
    @PostMapping
    public void createArticle(@RequestBody ArticleRequestDTO articleRequestDTO){
        articleService.saveArticle(articleRequestDTO.toEntity());
    }

    @Operation(summary = "게시글 수정", description = "게시글을 수정하는 API")
    @PutMapping("/{id}")
    public void updateArticle(@PathVariable Long id, @RequestBody Article article){
        @NotBlank
        Article existedarticle = articleService.findArticleById(id);
        existedarticle.builder()
                .title(article.getTitle())
                .content(article.getContent())
                .category(article.getCategory())
                .author(article.getAuthor())
                .build();
        articleService.saveArticle(existedarticle);
    }

    @Operation(summary = "게시글 삭제", description = "게시글을 삭제하는 API")
    @DeleteMapping("/{id}")
    public void deleteArticleById(@PathVariable @NotNull Long id){articleService.deleteArticleById(id);}

}
