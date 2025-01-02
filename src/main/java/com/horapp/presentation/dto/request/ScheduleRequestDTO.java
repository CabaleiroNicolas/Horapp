package com.horapp.presentation.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ScheduleRequestDTO {
    @NotEmpty(message = "The courseGroup must not be empty.")
    @Size(min = 1, max = 10, message = "The courseGroup must be between 5 and 10 characters.")
    private String courseGroup;
    @NotNull(message = "The idCourse must not be null.")
    @Positive(message = "The idCourse must be a positive number.")
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
