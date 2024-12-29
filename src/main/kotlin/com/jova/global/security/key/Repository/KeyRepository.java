package com.jova.global.security.key.Repository;

import com.jova.global.security.key.Entity.Key;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyRepository extends JpaRepository<Key,String> {
}
