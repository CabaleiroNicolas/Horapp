package com.horapp.presentation.dto.response;

public record UserResponseDTO(Long id,
                              String username,
                              String name,
                              String lastname,
                              String email) {
}
