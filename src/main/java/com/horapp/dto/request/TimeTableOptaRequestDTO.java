package com.horapp.dto.request;

import java.util.List;

public class TimeTableOptaRequestDTO {
    private Long idUser;
    private List<Long> coursesId;

    public TimeTableOptaRequestDTO() {
    }

    public TimeTableOptaRequestDTO(Long idUser, List<Long> coursesId) {
        this.idUser = idUser;
        this.coursesId = coursesId;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public List<Long> getCoursesId() {
        return coursesId;
    }

    public void setCoursesId(List<Long> coursesId) {
        this.coursesId = coursesId;
    }
}
