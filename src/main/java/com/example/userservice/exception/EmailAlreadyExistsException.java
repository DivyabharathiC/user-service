package com.example.userservice.exception;

public class EmailAlreadyExistsException  extends RuntimeException{
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}