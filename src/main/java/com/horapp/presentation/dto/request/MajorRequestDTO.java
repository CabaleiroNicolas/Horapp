package com.horapp.presentation.dto.request;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record MajorRequestDTO (
        @NotEmpty(message = "The majorName must not be empty.")
        @Size(max = 50, message = "The majorName must be max 50 characters.")
        String majorName,
        @Positive(message = "The userId must be a positive number.")
        Long userId)
        {
}
