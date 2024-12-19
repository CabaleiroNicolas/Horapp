package com.horapp.presentation.dto.response;

import java.util.List;

public class MajorResponseDTO {
    private Long idMajor;
    private String majorName;
    private String username;
    private List<String> courses;

    public MajorResponseDTO() {
    }

    public MajorResponseDTO(Long idMajor, String majorName, String username, List<String> courses) {
        this.idMajor = idMajor;
        this.majorName = majorName;
        this.username = username;
        this.courses = courses;
    }

    public Long getIdMajor() {
        return idMajor;
    }

    public void setIdMajor(Long idMajor) {
        this.idMajor = idMajor;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
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
