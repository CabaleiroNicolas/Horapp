package com.horapp.optaplanner.modeldomainOP;


import java.time.DayOfWeek;
import java.time.LocalTime;


public class DayAndTimeOptaPlanner implements Comparable<DayAndTimeOptaPlanner>{

    private final DayOfWeek day;
    private final LocalTime startTime;
    private final LocalTime endTime;

    public DayAndTimeOptaPlanner(DayOfWeek day, LocalTime startTime, LocalTime endTime) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public DayOfWeek getDay() {
        return day;
    }

    @Override
    public int compareTo(DayAndTimeOptaPlanner other) {
        int dayComparison = day.compareTo(other.day);
        if (dayComparison != 0) {
            return dayComparison;
        }
        return startTime.compareTo(other.startTime);
    }
}
