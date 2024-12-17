package com.horapp.presentation.dto;

import java.util.List;

public class FeedbackRequestDTO {
    private Long idFeedback;
    private String descriptionName;
    private List<Long> categoryId;

    public FeedbackRequestDTO() {
    }

    public FeedbackRequestDTO(Long idFeedback, String descriptionName, List<Long> categoryId) {
        this.idFeedback = idFeedback;
        this.descriptionName = descriptionName;
        this.categoryId = categoryId;
    }

    public Long getIdFeedback() {
        return idFeedback;
    }

    public void setIdFeedback(Long idFeedback) {
        this.idFeedback = idFeedback;
    }

    public String getDescriptionName() {
        return descriptionName;
    }

    public void setDescriptionName(String descriptionName) {
        this.descriptionName = descriptionName;
    }

    public List<Long> getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(List<Long> categoryId) {
        this.categoryId = categoryId;
    }
}
