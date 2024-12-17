package com.horapp.service;

import com.horapp.persistence.entity.Major;
import com.horapp.presentation.dto.MajorDTO;

import java.util.List;
import java.util.Optional;

public interface MajorService {
    MajorDTO saveMajor(MajorDTO majorDTO);
    List<MajorDTO> findAll();
    MajorDTO findById(Long id);
    Major findEntityById(Long id);
    Optional<MajorDTO> updateById(MajorDTO majorDTO, long id);
    String deleteById(Long id);
}
