package com.horapp.service;

import com.horapp.persistence.entity.TimeTable;
import com.horapp.presentation.dto.request.TimeTableRequestDTO;
import com.horapp.presentation.dto.response.TimeTableResponseDTO;

import java.util.List;

public interface TimeTableService {
    List<TimeTableResponseDTO> findAll();

    TimeTableResponseDTO findById(Long id);

    //TimeTableResponseDTO save(TimeTableRequestDTO timeTableRequestDTO);

    String deleteById(Long id);
}
