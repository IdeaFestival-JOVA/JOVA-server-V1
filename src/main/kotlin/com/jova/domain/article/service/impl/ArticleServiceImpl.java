package com.jova.domain.article.service.impl;

import com.jova.domain.article.entity.Article;
import com.jova.domain.article.repository.ArticleRepositiory;

import com.jova.domain.article.service.ArticleService;
import com.jova.domain.user.UserMajor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepositiory articleRepositiory;

    @Override
    public List<Article> findAll() {
        return articleRepositiory.findAll();
    }

    @Override
    public void saveArticle(Article article){

        articleRepositiory.save(article);
    }

    @Override
    public Article findArticleById(Long id) {
        return articleRepositiory.findById(id).orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
    }
    @Override
    public List<Article> findArticleByMajor(UserMajor major) {
        return articleRepositiory.findArticlesByCategory(major);
    }
    @Override
    public void deleteArticleById(Long id){
        articleRepositiory.deleteById(id);
    }

}
