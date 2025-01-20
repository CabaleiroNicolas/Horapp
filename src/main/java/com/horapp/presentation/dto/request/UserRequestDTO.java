package com.horapp.presentation.dto.request;

import jakarta.validation.constraints.*;

public record UserRequestDTO(@NotEmpty(message = "The username must not be empty.")
                             @Size(min = 5, max = 20, message = "Username must be between 5 and 20 characters")
                             String username,
                             @NotEmpty(message = "The name must not be empty")
                             @Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
                             String name,
                             @NotEmpty(message = "The lastname must not be empty")
                             @Size(min = 2, max = 20, message = "Lastname must be between 2 and 20 characters")
                             String lastname,
                             @NotEmpty(message = "The email must not by empty.")
                             @Email(message = "The email must be in valid format.")
                             String email,
                             @NotEmpty(message = "The password must not be empty")
                             @Size(min = 8, message = "The password must be at least 8 characters long.")
                             @Pattern(
                                     regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$#!%*?&])[A-Za-z\\d@$#!%*?&]{8,}$",
                                     message = "The password must contain at least one uppercase letter, one lowercase letter, one number, and one special character."
                             )
                             String password) {
}