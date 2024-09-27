package com.jova.domain.auth.repository;

import com.jova.domain.auth.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepositiory extends JpaRepository<ArticleEntity, Long> {}
