package com.horapp.presentation.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CourseRequestDTO {
    @NotEmpty(message = "The courseName must not be empty.")
    @Size(min = 5, max = 30, message = "The courseName must be between 5 and 30 characters")
    private String courseName;
    @NotNull(message = "The majorId must not be null.")
    @Positive(message = "The majorId must be a positive number.")
    private Long majorId;
    @Positive(message = "The userId must be a positive number.")
    private Long userId;
    @Positive(message = "The tableId must be a positive number.")
    private Long tableId;
    public CourseRequestDTO() {
    }

    public CourseRequestDTO(String courseName, Long majorId) {
        this.courseName = courseName;
        this.majorId = majorId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Long getMajorId() {
        return majorId;
    }

    public void setMajorId(Long majorId) {
        this.majorId = majorId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }
}
