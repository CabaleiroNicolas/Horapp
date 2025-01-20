package com.horapp.presentation.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ScheduleRequestDTO(@NotEmpty(message = "The courseGroup must not be empty.")
                                 @Size(min = 1, max = 10, message = "The courseGroup must be between 5 and 10 characters.")
                                 String courseGroup,
                                 @NotNull(message = "The idCourse must not be null.")
                                 @Positive(message = "The idCourse must be a positive number.")
                                 Long idCourse) {
}
