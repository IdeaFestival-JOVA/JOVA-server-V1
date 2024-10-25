package com.jova.domain.user.Service;

import com.jova.domain.user.Entity.User;
import com.jova.domain.user.Repository.UserRepository;
import com.jova.domain.user.UserMajor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findByMajor(UserMajor major) {
        return userRepository.findByMajor(major);
    }


}
