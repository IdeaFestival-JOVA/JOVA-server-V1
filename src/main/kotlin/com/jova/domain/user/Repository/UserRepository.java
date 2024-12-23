package com.jova.domain.user.Repository;

import com.jova.domain.user.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByGrade(Long grade);
    Optional<User> findUserByName(String name);
}
