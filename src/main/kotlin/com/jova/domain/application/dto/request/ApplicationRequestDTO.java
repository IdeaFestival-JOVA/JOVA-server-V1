package com.jova.domain.application.dto.request;

import com.jova.domain.application.entity.Application;
import com.jova.domain.auth.entity.Auth;
import com.jova.domain.user.UserMajor;
import lombok.Builder;

@Builder
public class ApplicationRequestDTO {
    private UserMajor major;
    private String content;
    private Auth auth;
    private String createdAt;

    public Application toEntity(){
        return Application.builder()
                .major(major)
                .content(content)
                .createdAt(createdAt)
                .build();
    }
}
