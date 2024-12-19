package com.horapp.service;

import com.horapp.presentation.dto.request.DayAndTimeRequestDTO;
import com.horapp.presentation.dto.response.DayAndTimeResponseDTO;

import java.util.List;

public interface DayAndTimeService {
    List<DayAndTimeResponseDTO> findAll();
    DayAndTimeResponseDTO findById(Long id);
    DayAndTimeResponseDTO save(DayAndTimeRequestDTO dayAndTimeRequestDTO);
}
