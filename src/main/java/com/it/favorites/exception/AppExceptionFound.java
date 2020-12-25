package com.it.favorites.exception;

import org.springframework.http.HttpStatus;

public class AppExceptionFound extends AppException {
    public AppExceptionFound() {
        this(HttpStatus.FOUND.toString());
    }

    public AppExceptionFound(String message) {
        super(HttpStatus.FOUND, message);
    }
}
