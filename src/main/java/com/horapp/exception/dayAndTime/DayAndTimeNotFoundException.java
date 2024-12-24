package com.horapp.exception.dayAndTime;

public class DayAndTimeNotFoundException extends RuntimeException{
    public DayAndTimeNotFoundException(Long id) {
        super("DayAndTime not found With id = " + id);
    }

}
