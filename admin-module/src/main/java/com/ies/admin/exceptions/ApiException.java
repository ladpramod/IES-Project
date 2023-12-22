package com.ies.admin.exceptions;

public class ApiException extends RuntimeException {

    public ApiException(String message) {
        super(message);
    }

    public ApiException() {
        super("Resource not Found!");
    }
}
