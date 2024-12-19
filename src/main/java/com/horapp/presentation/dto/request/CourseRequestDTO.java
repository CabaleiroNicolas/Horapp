package com.horapp.presentation.dto.request;

public class CourseRequestDTO {
    private String courseName;
    private Long majorId;
    private Long userId;
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
