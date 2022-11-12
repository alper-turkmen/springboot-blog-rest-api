package com.springboot.ecommerceproject.exception;

import org.springframework.http.HttpStatus;

public class ECommerceAPIException extends RuntimeException{
    private HttpStatus status;
    private String message;

    public ECommerceAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public ECommerceAPIException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
