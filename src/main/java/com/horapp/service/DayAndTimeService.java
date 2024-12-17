package com.horapp.service;

import com.horapp.presentation.dto.DayAndTimeRequestDTO;
import com.horapp.presentation.dto.DayAndTimeResponseDTO;

import java.util.List;

public interface DayAndTimeService {
    List<DayAndTimeResponseDTO> findAll();
    DayAndTimeResponseDTO findById(Long id);
    DayAndTimeResponseDTO save(DayAndTimeRequestDTO dayAndTimeRequestDTO);
}
