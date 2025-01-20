package com.horapp.presentation.dto.response;

import java.util.List;

public record TimeTableResponseDTO(Long id,
                                   String username,
                                   List<String> courses) {
}
