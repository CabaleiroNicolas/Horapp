package com.horapp.service;

import com.horapp.presentation.dto.request.FeedbackRequestDTO;
import com.horapp.presentation.dto.response.FeedbackResponseDTO;

import java.util.List;

public interface FeedbackService {
    FeedbackResponseDTO save(FeedbackRequestDTO feedbackRequestDTO);
    List<FeedbackResponseDTO> findAll();
    FeedbackResponseDTO findById(Long id);
}
