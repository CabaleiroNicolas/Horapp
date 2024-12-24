package com.horapp.exception.Schedule;

public class ScheduleNotFoundException extends RuntimeException{
    public ScheduleNotFoundException(Long id) {
        super("Schedule not found with id = " + id);
    }
}
