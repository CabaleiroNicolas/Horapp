package com.horapp.presentation.dto.response;

import java.util.List;

public class FeedbackResponseDTO {
    private Long idFeedback;
    private String descriptionName;
    private List<String> categories;
    private String course;

    public FeedbackResponseDTO() {
    }

    public FeedbackResponseDTO(Long idFeedback, String descriptionName, List<String> categories, String course) {
        this.idFeedback = idFeedback;
        this.descriptionName = descriptionName;
        this.categories = categories;
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
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

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
