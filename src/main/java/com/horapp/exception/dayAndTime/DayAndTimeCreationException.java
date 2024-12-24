package com.horapp.exception.dayAndTime;

public class DayAndTimeCreationException extends RuntimeException{
    public DayAndTimeCreationException(String message) {
        super(message);
    }

    public DayAndTimeCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
