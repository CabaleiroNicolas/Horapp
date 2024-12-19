package com.horapp.presentation.dto.response;

public class CategoryResponseDTO {
    private Long idCategory;
    private String categoryName;
    private String descriptionName;

    public CategoryResponseDTO() {
    }

    public CategoryResponseDTO(Long idCategory, String categoryName, String descriptionName) {
        this.idCategory = idCategory;
        this.categoryName = categoryName;
        this.descriptionName = descriptionName;
    }

    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
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
