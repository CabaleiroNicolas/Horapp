package com.horapp.presentation.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CourseRequestDTO (@NotEmpty(message = "The courseName must not be empty.")
                                @Size(min = 5, max = 30, message = "The courseName must be between 5 and 30 characters")
                                String courseName,
                                @NotNull(message = "The majorId must not be null.")
                                @Positive(message = "The majorId must be a positive number.")
                                Long majorId,
                                @Positive(message = "The userId must be a positive number.")
                                Long userId,
                                @Positive(message = "The tableId must be a positive number.")
                                Long tableId) {
}
