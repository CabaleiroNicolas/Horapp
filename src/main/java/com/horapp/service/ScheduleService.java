package com.horapp.service;

import com.horapp.persistence.entity.Schedule;
import com.horapp.presentation.dto.request.ScheduleRequestDTO;
import com.horapp.presentation.dto.response.ScheduleResponseDTO;

import java.util.List;

public interface ScheduleService {
    List<ScheduleResponseDTO> findAll();

    ScheduleResponseDTO findById(Long id);

    ScheduleResponseDTO  save(ScheduleRequestDTO scheduleRequestDTO);

    Schedule findEntityById(Long idSchedule);
}
