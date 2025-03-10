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
    public String toString() {
        return "DayAndTimeOptaPlanner{" +
                "day=" + day +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

    public boolean overlaps(DayAndTimeOptaPlanner other) {
        return day.equals(other.day) &&
                !(endTime.isBefore(other.startTime) || startTime.isAfter(other.endTime));
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
