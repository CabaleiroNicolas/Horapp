package com.horapp.optaplanner.modeldomainOP;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

import java.util.List;

@PlanningEntity
public class CourseOptaPlanner {

    @PlanningId
    private Long id;

    @PlanningVariable(valueRangeProviderRefs = "schedulesRange")
    private ScheduleOptaPlanner AssignedSchedule;

    @ValueRangeProvider(id = "schedulesRange")
    private List<ScheduleOptaPlanner> availableSchedules;

    private String courseName;

    public CourseOptaPlanner() {
    }

    public ScheduleOptaPlanner getAssignedSchedule() {
        return AssignedSchedule;
    }
    public String getCourseName() {
        return courseName;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public List<ScheduleOptaPlanner> getAvailableSchedules() {
        return availableSchedules;
    }
    public void setAvailableSchedules(List<ScheduleOptaPlanner> availableSchedules) {
        this.availableSchedules = availableSchedules;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }


}
