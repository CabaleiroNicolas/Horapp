package com.horapp.presentation.dto.response;

import java.util.List;

public record CourseResponseDTO(Long idCourse,
                                String courseName,
                                String majorName,
                                String username,
                                Long tableId,
                                List<String> schedules) {
}

