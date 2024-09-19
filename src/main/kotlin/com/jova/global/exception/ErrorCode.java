package com.jova.global.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
    USER_NOT_FOUND((short) 404, "Auth를 찾을 수 없습니다."), EXPIRED_TOKEN((short) 401, "Token이 만료되었습니다."), EXPIRED_REFRESH_TOKEN((short) 401, "만료된 Refresh Token 입니다."), INVALID_TOKEN_TYPE((short) 401, "유효하지 않은 Token 타입입니다."), INVALID_TOKEN((short) 401, "유효하지 않은 Token 입니다.");
    private final short status;
    private final String message;

    ErrorCode(short status, String message) {
        this.status = status;
        this.message = message;
    }
}