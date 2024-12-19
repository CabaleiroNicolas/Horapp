package com.horapp.presentation.dto.request;


public class CategoryRequestDTO {
    private String categoryName;
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
