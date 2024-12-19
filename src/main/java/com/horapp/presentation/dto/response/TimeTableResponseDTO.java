package com.horapp.presentation.dto.response;

import java.util.List;

public class TimeTableResponseDTO {
    private String username;
    private List<String> courses;

    public TimeTableResponseDTO() {
    }

    public TimeTableResponseDTO(String username, List<String> courses) {
        this.username = username;
        this.courses = courses;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }
}
