package com.horapp.service;

import com.horapp.presentation.dto.FeedbackRequestDTO;
import com.horapp.presentation.dto.FeedbackResponseDTO;

import java.util.List;

public interface FeedbackService {
    FeedbackRequestDTO save(FeedbackRequestDTO feedbackRequestDTO);
    List<FeedbackResponseDTO> findAll();
    FeedbackResponseDTO findById(Long id);
}
