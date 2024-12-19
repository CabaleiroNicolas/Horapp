package com.horapp.optaplanner.domain;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class ScheduleOptaPlanner {
    private String courseGroup;

    private ArrayList<DayAndTimeOptaPlanner> dayAndTimes;


    //public ArrayList<DayAndTimeOptaPlanner> getDayAndTimes() {
    //    return dayAndTimes;
   //}


    public ScheduleOptaPlanner() {
    }

    public ScheduleOptaPlanner(String courseGroup, ArrayList<DayAndTimeOptaPlanner> dayAndTimes) {
        this.courseGroup = courseGroup;
        this.dayAndTimes = dayAndTimes;
    }

    public void setDayAndTimes(ArrayList<DayAndTimeOptaPlanner> dayAndTimes) {
        this.dayAndTimes = dayAndTimes;
    }

    public String getCourseGroup() {
        return courseGroup;
    }

    public void setCourseGroup(String courseGroup) {
        this.courseGroup = courseGroup;
    }

    public void sortDayAndTimes() {
        dayAndTimes.sort(Comparator.naturalOrder());
    }

    public List<DayAndTimeOptaPlanner> getDayAndTimes() {
        if (dayAndTimes == null || dayAndTimes.isEmpty()) {
            throw new IllegalStateException("No hay horarios definidos");
        }
        return dayAndTimes;
    }

    @Override
    public String toString() {
        return "ScheduleOptaPlanner{" +
                "courseGroup='" + courseGroup + '\'' +
                ", dayAndTimes=" + dayAndTimes +
                '}';
    }
}
