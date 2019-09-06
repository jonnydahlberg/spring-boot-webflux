package com.example.demo.exception;

import lombok.Value;
import org.springframework.http.HttpStatus;

@Value
public class UserAlreadyExists extends RuntimeException {
    private HttpStatus status = HttpStatus.CONFLICT;

    public UserAlreadyExists(String message) {
        super(message);
    }
}
