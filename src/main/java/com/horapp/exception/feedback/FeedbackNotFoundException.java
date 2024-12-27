package com.horapp.exception.feedback;

public class FeedbackNotFoundException extends RuntimeException{

    public FeedbackNotFoundException(Long id) {
        super("Feedback not found with id " + id);
    }
}
