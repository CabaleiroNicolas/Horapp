package com.horapp.presentation.dto.request;

public class TimeTableRequestDTO {
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
