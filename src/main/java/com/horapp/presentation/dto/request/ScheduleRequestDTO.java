package com.horapp.presentation.dto.request;

public class ScheduleRequestDTO {
    private String courseGroup;
    private Long idCourse;

    public ScheduleRequestDTO(String courseGroup, Long idCourse) {
        this.courseGroup = courseGroup;
        this.idCourse = idCourse;
    }

    public ScheduleRequestDTO() {
    }

    public String getCourseGroup() {
        return courseGroup;
    }

    public void setCourseGroup(String courseGroup) {
        this.courseGroup = courseGroup;
    }

    public Long getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(Long idCourse) {
        this.idCourse = idCourse;
    }
}
