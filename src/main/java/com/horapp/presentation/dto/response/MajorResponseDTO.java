package com.horapp.presentation.dto.response;

import java.util.List;

public record MajorResponseDTO(Long idMajor,
                               String majorName,
                               String username,
                               List<String> courses) {
}
