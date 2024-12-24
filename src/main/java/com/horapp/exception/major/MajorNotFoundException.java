package com.horapp.exception.major;

public class MajorNotFoundException extends RuntimeException{
    public MajorNotFoundException(Long id) {
        super("Major not found with id = " + id);
    }

}
