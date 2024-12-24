package com.horapp.exception.Schedule;

public class ScheduleCreationException extends RuntimeException{
    public ScheduleCreationException(String message) {
        super(message);
    }

    public ScheduleCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
