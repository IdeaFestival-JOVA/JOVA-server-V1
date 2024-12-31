package com.jova.domain.auth.exception;

public class AuthAlreayExisted extends RuntimeException {
    public AuthAlreayExisted(String message) {
        super("Auth Alreay Existed: " + message);
    }
}
