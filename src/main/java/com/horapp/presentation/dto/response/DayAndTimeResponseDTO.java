package com.horapp.presentation.dto.response;

public class DayAndTimeResponseDTO {
    private Long idDayAndTime;
    private String day;
    private String time;

    public DayAndTimeResponseDTO() {
    }

    public DayAndTimeResponseDTO(Long idDayAndTime, String day, String time) {
        this.idDayAndTime = idDayAndTime;
        this.day = day;
        this.time = time;
    }

    public Long getIdDayAndTime() {
        return idDayAndTime;
    }

    public void setIdDayAndTime(Long idDayAndTime) {
        this.idDayAndTime = idDayAndTime;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
