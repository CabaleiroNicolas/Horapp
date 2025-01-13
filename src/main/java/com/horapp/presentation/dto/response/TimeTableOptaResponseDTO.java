package com.horapp.presentation.dto.response;

import com.horapp.presentation.dto.response.ScheduleAssignedDTO;

import java.util.List;

public record TimeTableOptaResponseDTO(
        List<ScheduleAssignedDTO> scheduleAssignedList
) {
}
