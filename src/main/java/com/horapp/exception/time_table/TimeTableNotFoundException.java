package com.horapp.exception.time_table;

public class TimeTableNotFoundException extends RuntimeException{
    public TimeTableNotFoundException(Long id) {
        super("Time Table not found with id = " + id);
    }
}
