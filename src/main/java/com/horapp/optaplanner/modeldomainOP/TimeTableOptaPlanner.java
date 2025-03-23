package com.horapp.optaplanner.modeldomainOP;

import org.optaplanner.core.api.domain.solution.*;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;


@PlanningSolution
@Component
public class TimeTableOptaPlanner {

    @PlanningEntityCollectionProperty
    private List<CourseOptaPlanner> courses;
    @ProblemFactCollectionProperty
    private List<DayAndTimeOptaPlanner> allDayAndTimes;
    @PlanningScore
    private HardSoftScore score;

    @ProblemFactProperty
    private final Integer minimumCoursePerDay = 1;
    @ProblemFactProperty
    private final LocalTime earliestStartTime = LocalTime.of(14,0);
    @ProblemFactProperty
    private final LocalTime latestEndTime =  LocalTime.of(20,30);;

    public TimeTableOptaPlanner() {
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


    public LocalTime getLatestEndTime() {
        return latestEndTime;
    }

    public LocalTime getEarliestStartTime() {
        return earliestStartTime;
    }

    public Integer getMinimumCoursePerDay() {
        return minimumCoursePerDay;
    }



}
