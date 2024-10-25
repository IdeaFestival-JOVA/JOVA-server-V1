package com.jova.domain.article.repository;

import com.jova.domain.article.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepositiory extends JpaRepository<ArticleEntity, Long> {

}
