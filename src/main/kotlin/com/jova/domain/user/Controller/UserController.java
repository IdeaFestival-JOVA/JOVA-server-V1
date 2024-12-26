package com.jova.domain.user.Controller;

import com.jova.domain.user.Entity.User;
import com.jova.domain.user.Service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/{id}")
public class UserController {

    private final UserServiceImpl userService;

}
