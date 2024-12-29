package com.jova.domain.article.repository;

import com.jova.domain.article.entity.Article;
import com.jova.domain.user.UserMajor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepositiory extends JpaRepository<Article, Long> {
    List<Article> findArticlesByCategory(UserMajor category);
}
