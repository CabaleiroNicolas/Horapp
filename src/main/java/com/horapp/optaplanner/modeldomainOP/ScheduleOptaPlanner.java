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

    public ScheduleOptaPlanner(String courseGroup, List<DayAndTimeOptaPlanner> dayAndTimes) {
        this.courseGroup = courseGroup;
        this.dayAndTimes = dayAndTimes;
    }

    public <E> ScheduleOptaPlanner(List<E> es) {
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
