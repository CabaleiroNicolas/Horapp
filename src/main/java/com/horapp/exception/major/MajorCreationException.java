package com.horapp.exception.major;

public class MajorCreationException extends RuntimeException{
    public MajorCreationException(String message) {
        super(message);
    }

    public MajorCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
