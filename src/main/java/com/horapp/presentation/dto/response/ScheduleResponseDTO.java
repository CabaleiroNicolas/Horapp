package com.horapp.presentation.dto.response;

import java.util.List;

public class ScheduleResponseDTO {
    private Long idSchedule;
    private String courseGroup;
    private List<String> days;

    public ScheduleResponseDTO() {
    }

    public ScheduleResponseDTO(Long idSchedule, String courseGroup, List<String> days) {
        this.idSchedule = idSchedule;
        this.courseGroup = courseGroup;
        this.days = days;
    }

    public Long getIdSchedule() {
        return idSchedule;
    }

    public void setIdSchedule(Long idSchedule) {
        this.idSchedule = idSchedule;
    }

    public String getCourseGroup() {
        return courseGroup;
    }

    public void setCourseGroup(String courseGroup) {
        this.courseGroup = courseGroup;
    }

    public List<String> getDays() {
        return days;
    }

    public void setDays(List<String> days) {
        this.days = days;
    }

}
