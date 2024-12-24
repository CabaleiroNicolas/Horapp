package com.horapp.presentation.dto.response;

import java.util.List;

public class CourseResponseDTO {
    private Long idCourse;
    private String courseName;
    private String majorName;
    private String username;
    private Long tableId;
    private List<String> schedules;

    public CourseResponseDTO() {
    }

    public CourseResponseDTO(String courseName, String majorName, List<String> schedules) {
        this.courseName = courseName;
        this.majorName = majorName;
        this.schedules = schedules;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public List<String> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<String> schedules) {
        this.schedules = schedules;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public Long getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(Long idCourse) {
        this.idCourse = idCourse;
    }
}

