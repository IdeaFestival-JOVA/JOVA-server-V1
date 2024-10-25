package com.jova.domain.application.repository;

import com.jova.domain.application.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Optional<Application> findByArticleApplicationId(Long article_id, Long application_id);
}
