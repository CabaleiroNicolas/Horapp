package com.horapp.service;

import com.horapp.persistence.entity.Major;
import com.horapp.presentation.dto.request.MajorRequestDTO;
import com.horapp.presentation.dto.response.MajorResponseDTO;

import java.util.List;
import java.util.Optional;

public interface MajorService {
    MajorResponseDTO saveMajor(MajorRequestDTO majorRequestDTO);
    List<MajorResponseDTO> findAll();
    MajorResponseDTO findById(Long id);
    Major findEntityById(Long id);
    String deleteById(Long id);
}
