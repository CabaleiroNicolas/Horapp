package com.horapp.service;

import com.horapp.presentation.dto.request.ScheduleRequestDTO;
import com.horapp.presentation.dto.response.ScheduleResponseDTO;

import java.util.List;

public interface ScheduleService {
    List<ScheduleResponseDTO> findAllByCourse(Long id);

    ScheduleResponseDTO findById(Long id);

    ScheduleResponseDTO  save(ScheduleRequestDTO scheduleRequestDTO);

}
