package com.horapp.optaplanner.modeldomainOP;


import org.optaplanner.core.api.domain.lookup.PlanningId;

import java.util.Comparator;
import java.util.List;


public class ScheduleOptaPlanner {

    @PlanningId
    private Long id;
    private String courseGroup;
    private List<DayAndTimeOptaPlanner> dayAndTimes;
    private CourseOptaPlanner course;


    public ScheduleOptaPlanner() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CourseOptaPlanner getCourse() {
        return course;
    }

    public void setCourse(CourseOptaPlanner course) {
        this.course = course;
    }

    public void setDayAndTimes(List<DayAndTimeOptaPlanner> dayAndTimes) {
        this.dayAndTimes = dayAndTimes;
    }

    public String getCourseGroup() {
        return courseGroup;
    }

    public void setCourseGroup(String courseGroup) {
        this.courseGroup = courseGroup;
    }

    public List<DayAndTimeOptaPlanner> getDayAndTimes() {
        return dayAndTimes;
    }

}
