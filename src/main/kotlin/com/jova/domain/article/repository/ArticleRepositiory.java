package com.jova.domain.article.repository;

import com.jova.domain.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepositiory extends JpaRepository<Article, Long> {

}
