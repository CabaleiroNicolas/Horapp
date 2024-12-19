package com.horapp.presentation.dto.request;


public class MajorRequestDTO {
    private String majorName;
    private String username;

    public MajorRequestDTO() {
    }

    public MajorRequestDTO(String majorName, String username) {
        this.majorName = majorName;
        this.username = username;
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
}
