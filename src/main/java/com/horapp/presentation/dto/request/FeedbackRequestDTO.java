package com.horapp.presentation.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;

public record FeedbackRequestDTO(@NotEmpty(message = "The descriptionName must not be empty.")
                                 @Size(min = 5, max = 50, message = "The descriptionName must be between 5 and 50 characters.")
                                 String descriptionName,
                                 @NotNull(message = "The categoryId list must not be null.")
                                 @Size(min = 1, message = "There must be at least one categoryId.")
                                 List<@Positive(message = "Each categoryId must be a positive number.") Long> categoryId,
                                 @Positive(message = "The courseId must be a positive number.")
                                 Long courseId) {
}
