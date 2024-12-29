package com.jova.domain.article.service;

import com.jova.domain.article.dto.request.ArticleRequestDTO;
import com.jova.domain.article.entity.Article;
import com.jova.domain.user.UserMajor;

import java.util.List;
import java.util.Optional;

public interface ArticleService {
    Article findArticleById(Long id);
    List<Article> findAll();
    void saveArticle(Article article);
    void deleteArticleById(Long id);
    List<Article> findArticleByMajor(UserMajor category);
}
