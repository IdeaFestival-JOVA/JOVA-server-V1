package com.jova.domain.user.Controller;

import com.jova.domain.user.Service.UserService;
import com.jova.domain.user.UserMajor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/{id}")
public class UserController {

    private final UserService userService;
}
