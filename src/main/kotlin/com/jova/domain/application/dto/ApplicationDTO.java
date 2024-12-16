package com.jova.domain.application.dto;

import com.jova.domain.application.entity.Application;
import com.jova.domain.auth.entity.Auth;
import com.jova.domain.user.UserMajor;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class ApplicationDTO {
    private UserMajor major;
    private String content;
    private Auth auth;
    private LocalDateTime createdAt;

    public Application toEntity(){
        return Application.builder()
                .major(major)
                .content(content)
                .auth(auth)
                .createdAt(createdAt)
                .build();
    }
}
