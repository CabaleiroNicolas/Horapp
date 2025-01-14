package com.horapp.presentation.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;

public record TimeTableOptaRequestDTO (
        @NotNull(message = "The coursesId list must not be null.")
        @Size(min = 1, message = "There must be at least one coursesId.")
        List<@Positive(message = "Each coursesId must be a positive number.") Long> coursesId

){

}
