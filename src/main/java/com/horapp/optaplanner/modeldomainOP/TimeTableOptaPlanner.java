package com.horapp.optaplanner.modeldomainOP;

import com.horapp.optaplanner.OptaPlannerConstraints;
import org.optaplanner.core.api.domain.solution.*;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@PlanningSolution
public class TimeTableOptaPlanner {

    @PlanningEntityCollectionProperty
    private List<CourseOptaPlanner> courses;
    @ProblemFactCollectionProperty
    private List<DayAndTimeOptaPlanner> allDayAndTimes;
    @PlanningScore
    private HardSoftScore score;

    @ProblemFactProperty
    private Integer minimumCoursePerDay;
    @ProblemFactProperty
    private LocalTime earliestStartTime;
    @ProblemFactProperty
    private LocalTime latestEndTime;

    //Mapa de restricciones disponibles
    @ProblemFactProperty
    private Map<OptaPlannerConstraints, Boolean> constraints;

    public TimeTableOptaPlanner() {
    }

    public TimeTableOptaPlanner(List<CourseOptaPlanner> courses, HardSoftScore score) {
        this.courses = courses;
        this.score = score;
    }

    public <E> TimeTableOptaPlanner(List<E> course1) {
    }

    public List<DayAndTimeOptaPlanner> getAllDayAndTimes() {
        return allDayAndTimes;
    }

    public void setAllDayAndTimes(List<DayAndTimeOptaPlanner> allDayAndTimes) {
        this.allDayAndTimes = allDayAndTimes;
    }

    public List<CourseOptaPlanner> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseOptaPlanner> courses) {
        this.courses = courses;
    }

    public HardSoftScore getScore() {
        return score;
    }

    public void setScore(HardSoftScore score) {
        this.score = score;
    }

    public void setEarliestStartTime(LocalTime earliestStartTime) {
        this.earliestStartTime = earliestStartTime;
    }

    public LocalTime getLatestEndTime() {
        return latestEndTime;
    }

    public void setLatestEndTime(LocalTime latestEndTime) {
        this.latestEndTime = latestEndTime;
    }

    public LocalTime getEarliestStartTime() {
        return earliestStartTime;
    }

    public Integer getMinimumCoursePerDay() {
        return minimumCoursePerDay;
    }

    public void setMinimumCoursePerDay(Integer minimumCoursePerDay) {
        this.minimumCoursePerDay = minimumCoursePerDay;
    }

    public boolean isConstraintActive(OptaPlannerConstraints constraint) {
        return constraints.getOrDefault(constraint, false);
    }

    public void setConstraintActive(OptaPlannerConstraints constraintId, boolean active) {
        constraints.put(constraintId, active);
    }
}
