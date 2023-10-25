package com.liftoff.notificationservice.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {

    private final HttpStatus status;
    private final String message;

    public BusinessException(String message) {
        super(message);

        this.status = HttpStatus.NOT_FOUND;
        this.message = message;
    }

    public BusinessException(String message, HttpStatus status) {
        super(message);

        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

}