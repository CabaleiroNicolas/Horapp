package com.horapp.presentation.dto.request;

import jakarta.validation.constraints.NotEmpty;

public class TimeTableRequestDTO {
    @NotEmpty(message = "The username must not be empty.")
    private String username;

    public TimeTableRequestDTO() {
    }

    public TimeTableRequestDTO(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
