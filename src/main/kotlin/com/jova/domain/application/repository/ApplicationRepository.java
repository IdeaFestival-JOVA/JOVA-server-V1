package com.jova.domain.application.repository;

import com.jova.domain.application.entity.Application;
import com.jova.domain.article.entity.Article;
import com.jova.domain.auth.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Optional<Application> findByArticle(Article article);
    List<Application> findByAuth(Auth auth);
}
