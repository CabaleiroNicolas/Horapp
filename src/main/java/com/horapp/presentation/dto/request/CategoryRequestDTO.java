package com.horapp.presentation.dto.request;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record CategoryRequestDTO (@NotEmpty(message = "The categoryName must not be empty.")
                                  @Size(min = 5, max = 20, message = "categoryName must be between 5 and 20 characters")
                                  String categoryName,
                                  @NotEmpty(message = "The descriptionName must not be empty.")
                                  String descriptionName) {}
