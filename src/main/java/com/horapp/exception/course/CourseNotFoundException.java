package com.horapp.exception.course;

public class CourseNotFoundException extends RuntimeException{
    public CourseNotFoundException(Long id) {
        super("Course not found with id = " + id);
    }

    public CourseNotFoundException(String message) {
        super(message);
    }
}
