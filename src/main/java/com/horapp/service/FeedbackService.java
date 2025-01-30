package com.horapp.service;

import com.horapp.presentation.dto.request.FeedbackRequestDTO;
import com.horapp.presentation.dto.response.FeedbackResponseDTO;

public interface FeedbackService {
    FeedbackResponseDTO save(FeedbackRequestDTO feedbackRequestDTO);
}
