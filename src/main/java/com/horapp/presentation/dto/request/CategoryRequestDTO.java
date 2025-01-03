package com.horapp.presentation.dto.request;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CategoryRequestDTO {
    @NotEmpty(message = "The categoryName must not be empty.")
    @Size(min = 5, max = 20, message = "categoryName must be between 5 and 20 characters")
    private String categoryName;
    @NotEmpty(message = "The descriptionName must not be empty.")
    private String descriptionName;

    public CategoryRequestDTO() {
    }

    public CategoryRequestDTO(String categoryName, String descriptionName) {
        this.categoryName = categoryName;
        this.descriptionName = descriptionName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescriptionName() {
        return descriptionName;
    }

    public void setDescriptionName(String descriptionName) {
        this.descriptionName = descriptionName;
    }
}
