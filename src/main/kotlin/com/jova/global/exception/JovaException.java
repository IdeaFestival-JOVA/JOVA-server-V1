package com.jova.global.exception;

import lombok.Getter;

@Getter
public class JovaException extends RuntimeException {
    private final ErrorCode errorCode;

    public JovaException(ErrorCode errorCode) {
        super();
        this.errorCode = errorCode;
    }
}