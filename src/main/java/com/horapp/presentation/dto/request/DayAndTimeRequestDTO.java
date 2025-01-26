package com.horapp.presentation.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record DayAndTimeRequestDTO(
        @NotNull(message = "The idSchedule must not be null.")
        @Positive(message = "The idSchedule must be a positive number.")
        Long idSchedule,
        @NotEmpty(message = "The day must not be empty.")
        @Pattern(regexp = "^(MONDAY|TUESDAY|WEDNESDAY|THURSDAY|FRIDAY)$", message = "The day must be a valid day of the week (e.g., MONDAY, TUESDAY).")
        String day,
        @NotEmpty(message = "The startTime must not be empty.")
        @Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d)$", message = "The startTime must be in the format HH:mm (e.g., 08:30).")
        String startTime,
        @NotEmpty(message = "The endTime must not be empty.")
        @Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d)$", message = "The endTime must be in the format HH:mm (e.g., 17:45).")
        String endTime
) {
}
