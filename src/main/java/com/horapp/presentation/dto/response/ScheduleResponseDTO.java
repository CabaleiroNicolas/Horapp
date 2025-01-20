package com.horapp.presentation.dto.response;

import java.util.List;

public record ScheduleResponseDTO(Long idSchedule,
                                  String courseGroup,
                                  List<String> days) {
}
