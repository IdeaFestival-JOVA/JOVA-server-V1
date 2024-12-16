package com.jova.domain.article.service;

import com.jova.domain.article.entity.Article;

import java.util.List;

public interface ArticleService {
    Article findArticleById(Long id);
    List<Article> findAll();
    Article saveArticle(Article article);
}
