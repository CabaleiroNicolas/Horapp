package com.horapp.optaplanner.modeldomainOP;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class DayAndTimeOptaPlanner implements Comparable<DayAndTimeOptaPlanner>{

    private DayOfWeek day;
    private LocalTime startTime;
    private LocalTime endTime;

    public DayAndTimeOptaPlanner() {
    }

    public DayAndTimeOptaPlanner(DayOfWeek day, LocalTime startTime, LocalTime endTime) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
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
