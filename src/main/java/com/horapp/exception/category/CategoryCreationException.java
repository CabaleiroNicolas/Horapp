package com.horapp.exception.category;

public class CategoryCreationException extends RuntimeException{
    public CategoryCreationException(String message) {
        super(message);
    }

    public CategoryCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
