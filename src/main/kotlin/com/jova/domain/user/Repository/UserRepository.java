package com.jova.domain.user.Repository;

import com.jova.domain.user.Entity.User;
import com.jova.domain.user.UserMajor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByMajor(UserMajor major);
}
