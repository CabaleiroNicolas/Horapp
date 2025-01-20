package com.horapp.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories_fb")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq_gen")
    @SequenceGenerator(name = "category_seq_gen", sequenceName = "categories_fb_id_seq", allocationSize = 1)
    private long idCategory;

    private String categoryName;

    private String descriptionName;

    private boolean deleted;

    @ManyToMany(mappedBy = "categoryList")
    private List<Feedback> feedbackList;

    public Category(long idCategory) {
        this.idCategory = idCategory;
    }

    public Category(String categoryName, String descriptionName) {
        this.categoryName = categoryName;
        this.descriptionName = descriptionName;
    }

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
