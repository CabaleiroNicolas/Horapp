package com.horapp.optaplanner.modeldomainOP;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import java.util.List;

@PlanningSolution
public class TimeTableOptaPlanner {

    @PlanningEntityCollectionProperty
    private List<CourseOptaPlanner> courses;
    @ProblemFactCollectionProperty
    private List<DayAndTimeOptaPlanner> allDayAndTimes;
    @PlanningScore
    private HardSoftScore score;

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


    @Override
    public String toString() {
        return "TimeTableOptaPlanner{" +
                "courses=" + courses +
                ", score=" + score +
                '}';
    }
}
