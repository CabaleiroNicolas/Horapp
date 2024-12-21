package com.horapp.optaplanner.modeldomainOP;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import java.util.ArrayList;
import java.util.Comparator;

@PlanningSolution
public class TimeTableOptaPlanner {

    @PlanningEntityCollectionProperty
    private ArrayList<CourseOptaPlanner> courses;


    @PlanningScore
    private HardSoftScore score;

    public TimeTableOptaPlanner() {
    }

    public TimeTableOptaPlanner(ArrayList<CourseOptaPlanner> courses, HardSoftScore score) {
        this.courses = courses;
        this.score = score;
    }

    public ArrayList<CourseOptaPlanner> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<CourseOptaPlanner> courses) {
        this.courses = courses;
    }

    public HardSoftScore getScore() {
        return score;
    }

    public void setScore(HardSoftScore score) {
        this.score = score;
    }


    public void sortSchedules() {
        for (CourseOptaPlanner course : courses) {
            // Ordenar los horarios disponibles de cada curso
            course.getAvailableSchedules().sort(Comparator.comparing(schedule ->
                    schedule.getDayAndTimes().get(0).getStartTime()));
        }
    }

}
