package com.jova.domain.article.service.impl;

import com.jova.domain.article.entity.Article;
import com.jova.domain.article.repository.ArticleRepositiory;

import com.jova.domain.article.service.ArticleService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepositiory articleRepositiory;

    public ArticleServiceImpl(ArticleRepositiory articleRepositiory) {
        this.articleRepositiory = articleRepositiory;
    }

    @Override
    public List<Article> findAll() {
        return articleRepositiory.findAll();
    }

    @Override
    public Article saveArticle(Article article) {
        articleRepositiory.save(article);
        return article;
    }

    @Override
    public Article findArticleById(Long id) {
        return articleRepositiory.findById(id).orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
    }

    public void deleteArticleById(Long id){
        articleRepositiory.deleteById(id);
    }

}
