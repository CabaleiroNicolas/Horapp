package com.horapp.presentation.dto.request;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record MajorRequestDTO (
        @NotEmpty(message = "The majorName must not be empty.")
        @Size(max = 50, message = "The majorName must be max 50 characters.")
        String majorName,
        @Size(min = 5, max = 30, message = "The username must be between 5 and 50 characters.")
        String username){
}
