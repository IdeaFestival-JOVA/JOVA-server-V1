package com.jova.domain.application.dto;

import com.jova.domain.application.entity.Application;
import com.jova.domain.auth.entity.Auth;
import com.jova.domain.user.UserMajor;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class ApplicationRequestDTO {
    private String title;
    private String content;
    private UserMajor major;
    private Auth auth;
    private LocalDateTime createdAt;

    @Builder
    ApplicationRequestDTO(String content, UserMajor major, Auth auth) {
        this.content = content;
        this.major = major;
        this.auth = auth;
    }

    public Application toEntity(){
        return Application.builder()
                .content(content)
                .major(major)
                .auth(auth)
                .build();
    }
}
