package com.horapp.presentation.dto.response;

import com.horapp.presentation.dto.response.ScheduleAssignedDTO;

import java.util.List;

public class TimeTableOptaResponseDTO {
    private String username;
    private String major;
    private List<ScheduleAssignedDTO> scheduleAssignedList;

    public TimeTableOptaResponseDTO() {
    }

    public TimeTableOptaResponseDTO(String username, String major, List<ScheduleAssignedDTO> scheduleAssignedList) {
        this.username = username;
        this.major = major;
        this.scheduleAssignedList = scheduleAssignedList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public List<ScheduleAssignedDTO> getScheduleAssignedList() {
        return scheduleAssignedList;
    }

    public void setScheduleAssignedList(List<ScheduleAssignedDTO> scheduleAssignedList) {
        this.scheduleAssignedList = scheduleAssignedList;
    }
}
