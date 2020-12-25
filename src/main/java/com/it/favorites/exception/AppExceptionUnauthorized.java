package com.it.favorites.exception;

import org.springframework.http.HttpStatus;

public class AppExceptionUnauthorized extends AppException {
    public AppExceptionUnauthorized() {
        this(HttpStatus.UNAUTHORIZED.toString());
    }

    public AppExceptionUnauthorized(String message) {
        super(HttpStatus.UNAUTHORIZED, message); }
}
