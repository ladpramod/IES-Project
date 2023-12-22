package com.ies.module.admin.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionsHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse> apiExceptionHandle(ApiException e){

        String message = e.getMessage();
        LocalDateTime dateTime = LocalDateTime.now();
        ApiResponse response = new ApiResponse(message,false, dateTime);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>>  validationExceptionHandler(MethodArgumentNotValidException ex){
        Map<String,String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(),error.getDefaultMessage());
        });
        return new ResponseEntity<>(errorMap,HttpStatus.BAD_REQUEST);
    }
}
