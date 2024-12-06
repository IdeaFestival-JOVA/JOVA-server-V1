package com.jova.domain.user.Service;

import com.jova.domain.user.Entity.User;
import com.jova.domain.user.Repository.UserRepository;
import com.jova.domain.user.UserMajor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findByMajor(UserMajor major) {
        return userRepository.findByMajor(major);
    }

    public void updateMajor(Long user_id, List<UserMajor> userMajors) {
        Optional<User> existUser = userRepository.findById(user_id);

        if (existUser.isPresent()) {
            User user = existUser.get();
            user.setMajor(userMajors);
            userRepository.save(user);
        }
        else {
            throw new RuntimeException("유저를 찾지 못했습니다.");
        }
    }

    public void setUserMajor(List<UserMajor> userMajors) {
        //userRepository.save(userMajors);
    }


}
