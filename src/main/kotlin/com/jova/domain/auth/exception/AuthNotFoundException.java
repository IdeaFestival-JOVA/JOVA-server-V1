package com.jova.domain.auth.exception;

import com.jova.global.exception.ErrorCode;
import com.jova.global.exception.JovaException;

public class AuthNotFoundException extends JovaException {
    public AuthNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}