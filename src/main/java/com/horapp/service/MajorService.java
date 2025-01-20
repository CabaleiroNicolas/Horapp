package com.horapp.service;

import com.horapp.presentation.dto.request.MajorRequestDTO;
import com.horapp.presentation.dto.response.MajorResponseDTO;

import java.util.List;

public interface MajorService {
    MajorResponseDTO saveMajor(MajorRequestDTO majorRequestDTO);
    List<MajorResponseDTO> findAll();
    MajorResponseDTO findById(Long id);
    String deleteById(Long id);
}
