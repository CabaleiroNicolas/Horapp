package com.horapp.presentation.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;

public class TimeTableOptaRequestDTO {
    @NotNull(message = "The idUser must not be null.")
    @Positive(message = "The idUser must be a positive number.")
    private Long idUser;
    @NotNull(message = "The coursesId list must not be null.")
    @Size(min = 1, message = "There must be at least one coursesId.")
    private List<@Positive(message = "Each coursesId must be a positive number.") Long> coursesId;

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
