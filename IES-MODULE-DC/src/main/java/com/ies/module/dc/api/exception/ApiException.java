package com.ies.module.dc.api.exception;

public class ApiException extends RuntimeException{

    public ApiException() {
    }

    public ApiException(String message) {
        super(message);
    }
}
