package com.horapp.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category")
    private long idCategory;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "description_name")
    private String descriptionName;

    private boolean deleted;

    @ManyToMany(mappedBy = "categoryList")
    private List<Feedback> feedbackList;

    public long getIdCategory() {
        return idCategory;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getDescriptionName() {
        return descriptionName;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public List<Feedback> getFeedbackList() {
        return feedbackList;
    }

    public void setIdCategory(long idCategory) {
        this.idCategory = idCategory;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setDescriptionName(String descriptionName) {
        this.descriptionName = descriptionName;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public void setFeedbackList(List<Feedback> feedbackList) {
        this.feedbackList = feedbackList;
    }

    @Override
    public String toString() {
        return "Category{" +
                ", categoryName='" + categoryName + '\'' +
                ", descriptionName='" + descriptionName + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}
