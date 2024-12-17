package com.horapp.presentation.dto;

public class DayAndTimeRequestDTO {
    private Long idSchedule;
    private String day;
    private String startTime;
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
