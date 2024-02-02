package com.mongodb.demo.exception;

import org.springframework.security.core.AuthenticationException;

import java.io.Serial;

public class UserNotFoundException extends AuthenticationException {

    public UserNotFoundException(String message) {
        super(message);
    }

    @Serial
    private static final long serialVersionUID = -3903611503370315872L;

}