package com.horapp.service;

import com.horapp.presentation.dto.response.TimeTableResponseDTO;


public interface TimeTableService {


    TimeTableResponseDTO findById(Long id);

    //TimeTableResponseDTO save(TimeTableRequestDTO timeTableRequestDTO);

    String deleteById(Long id);
}
