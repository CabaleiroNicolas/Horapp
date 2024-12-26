package com.horapp.exception.schedule;

public class ScheduleNotFoundException extends RuntimeException{
    public ScheduleNotFoundException(Long id) {
        super("Schedule not found with id = " + id);
    }
}
