package com.horapp.presentation.dto.request;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class MajorRequestDTO {
    @NotEmpty(message = "The majorName must not be empty.")
    @Size(min = 5, max = 20, message = "The majorName must be between 5 and 50 characters.")
    private String majorName;
    @Size(min = 5, max = 20, message = "The username must be between 5 and 50 characters.")
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
