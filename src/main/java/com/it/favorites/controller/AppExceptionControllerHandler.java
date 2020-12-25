package com.it.favorites.controller;

import com.it.favorites.exception.AppError;
import com.it.favorites.exception.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

/**
 * REST api内部定义异常处理控制器
 */
@ControllerAdvice
public class AppExceptionControllerHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<AppError> appException(AppException ex) {
        AppError error = new AppError(ex.getStatus().value(), ex.getMessage());
        ResponseEntity<AppError> entity = new ResponseEntity<AppError>(error, ex.getStatus());
        return entity;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<AppError> exception(IllegalArgumentException ex) {
        AppError error = new AppError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        ResponseEntity<AppError> entity = new ResponseEntity<AppError>(error, HttpStatus.BAD_REQUEST);
        return entity;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<AppError> exception(HttpMessageNotReadableException ex) {
        AppError error = new AppError(HttpStatus.BAD_REQUEST.value(), "序列化bean失败");
        ResponseEntity<AppError> entity = new ResponseEntity<AppError>(error, HttpStatus.BAD_REQUEST);
        return entity;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<AppError> exception(HttpRequestMethodNotSupportedException ex) {
        AppError error = new AppError(HttpStatus.METHOD_NOT_ALLOWED.value(), "不支持的HTTP方法");
        ResponseEntity<AppError> entity = new ResponseEntity<AppError>(error, HttpStatus.METHOD_NOT_ALLOWED);
        return entity;
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<AppError> exception(SQLException ex) {
        AppError error = new AppError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "数据库未响应");
        ResponseEntity<AppError> entity = new ResponseEntity<AppError>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        return entity;
    }

}