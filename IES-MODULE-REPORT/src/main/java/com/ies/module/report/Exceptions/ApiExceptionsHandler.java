package com.ies.module.report.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ApiExceptionsHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse> apiExceptionHandle(ApiException e){

        String message = e.getMessage();
        LocalDateTime dateTime = LocalDateTime.now();
        ApiResponse response = new ApiResponse(message,false,dateTime);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
