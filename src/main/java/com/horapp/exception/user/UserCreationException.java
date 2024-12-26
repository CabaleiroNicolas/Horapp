package com.horapp.exception.user;

public class UserCreationException extends RuntimeException{
    public UserCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserCreationException(String message) {
        super(message);
    }
}
