package com.horapp.service;

import com.horapp.persistence.entity.Schedule;
import com.horapp.presentation.dto.ScheduleRequestDTO;
import com.horapp.presentation.dto.ScheduleResponseDTO;

import java.util.List;
import java.util.Optional;

public interface ScheduleService {
    List<ScheduleResponseDTO> findAll();

    ScheduleResponseDTO findById(Long id);

    ScheduleResponseDTO  save(ScheduleRequestDTO scheduleRequestDTO);

    Schedule findEntityById(Long idSchedule);
}
