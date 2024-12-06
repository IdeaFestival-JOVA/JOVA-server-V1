package com.jova.domain.article.service;

import com.jova.domain.article.entity.Article;
import com.jova.domain.article.repository.ArticleRepositiory;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ArticleService {

    private final ArticleRepositiory articleRepositiory;

    public ArticleService(ArticleRepositiory articleRepositiory) {
        this.articleRepositiory = articleRepositiory;
    }
    public List<Article> findAll() {
        return articleRepositiory.findAll();
    }

    public Article saveArticle(Article article) {
        articleRepositiory.save(article);
        return article;
    }

    public Article findArticleById(Long id) {
        return articleRepositiory.findById(id).orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
    }

    public void deleteArticleById(Long id){
        articleRepositiory.deleteById(id);
    }

}
