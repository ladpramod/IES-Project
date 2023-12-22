package com.ies.module.report.Exceptions;

public class ApiException extends RuntimeException{

    public ApiException() {
    }

    public ApiException(String message) {
        super(message);
    }
}
