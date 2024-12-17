package com.horapp.presentation.dto;

public class CourseRequestDTO {
    private String courseName;
    private Long majorId;

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
}
