package com.horapp.presentation.dto.response;

import java.util.List;

public record FeedbackResponseDTO (Long idFeedback,
                                   String descriptionName,
                                   List<String> categories,
                                   String course){}
