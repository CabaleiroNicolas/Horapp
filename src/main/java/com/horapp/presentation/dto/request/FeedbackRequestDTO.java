package com.horapp.presentation.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;

public class FeedbackRequestDTO {
    @NotEmpty(message = "The descriptionName must not be empty.")
    @Size(min = 5, max = 50, message = "The descriptionName must be between 5 and 50 characters.")
    private String descriptionName;
    @NotNull(message = "The categoryId list must not be null.")
    @Size(min = 1, message = "There must be at least one categoryId.")
    private List<@Positive(message = "Each categoryId must be a positive number.") Long> categoryId;
    @Positive(message = "The courseId must be a positive number.")
    private Long courseId;
    public FeedbackRequestDTO() {
    }

    public FeedbackRequestDTO(String descriptionName, List<Long> categoryId, Long courseId) {
        this.descriptionName = descriptionName;
        this.categoryId = categoryId;
        this.courseId = courseId;
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

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
