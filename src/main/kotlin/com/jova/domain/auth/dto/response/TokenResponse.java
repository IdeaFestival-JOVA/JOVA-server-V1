package com.jova.domain.auth.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TokenResponse {
    String accessToken;
    String refreshToken;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime accessTokenExpiration;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime refreshTokenExpiration;
}
