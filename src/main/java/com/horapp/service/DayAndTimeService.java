package com.horapp.service;

import com.horapp.presentation.dto.request.DayAndTimeRequestDTO;
import com.horapp.presentation.dto.response.DayAndTimeResponseDTO;


public interface DayAndTimeService {
    DayAndTimeResponseDTO findById(Long id);
    DayAndTimeResponseDTO save(DayAndTimeRequestDTO dayAndTimeRequestDTO);
}
