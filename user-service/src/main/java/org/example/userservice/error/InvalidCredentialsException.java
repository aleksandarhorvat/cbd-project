package org.example.userservice.error;

import java.util.NoSuchElementException;

public class InvalidCredentialsException extends NoSuchElementException {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}