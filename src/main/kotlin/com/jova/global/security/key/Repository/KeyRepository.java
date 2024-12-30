package com.jova.global.security.key.Repository;

import com.jova.global.security.key.Entity.Key;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KeyRepository extends JpaRepository<Key,String> {
    boolean existsByKey(String key);
    @Query("SELECT k FROM Key k WHERE k.key = :key")
    Key findByKey(@Param("key") String key);
}
