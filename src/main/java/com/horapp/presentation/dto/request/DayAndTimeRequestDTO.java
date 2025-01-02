package com.horapp.presentation.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public class DayAndTimeRequestDTO {
    @NotNull(message = "The idSchedule must not be null.")
    @Positive(message = "The idSchedule must be a positive number.")
    private Long idSchedule;
    @NotEmpty(message = "The day must not be empty.")
    @Pattern(
            regexp = "^(MONDAY|TUESDAY|WEDNESDAY|THURSDAY|FRIDAY)$",
            message = "The day must be a valid day of the week (e.g., MONDAY, TUESDAY)."
    )
    private String day;
    @NotEmpty(message = "The startTime must not be empty.")
    @Pattern(
            regexp = "^([01]\\d|2[0-3]):([0-5]\\d)$",
            message = "The startTime must be in the format HH:mm (e.g., 08:30)."
    )
    private String startTime;
    @NotEmpty(message = "The endTime must not be empty.")
    @Pattern(
            regexp = "^([01]\\d|2[0-3]):([0-5]\\d)$",
            message = "The endTime must be in the format HH:mm (e.g., 17:45)."
    )
    private String endTime;

    public DayAndTimeRequestDTO() {
    }

    public DayAndTimeRequestDTO(Long idSchedule, String day, String startTime, String endTime) {
        this.idSchedule = idSchedule;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getIdSchedule() {
        return idSchedule;
    }

    public void setIdSchedule(Long idSchedule) {
        this.idSchedule = idSchedule;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
