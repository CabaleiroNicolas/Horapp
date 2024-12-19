package com.horapp.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_feedback")
    private long idFeedback;
    @Column(name = "description_name")
    private String descriptionName;

    @ManyToMany
    @JoinTable(
            name = "feedback_category",
            joinColumns = @JoinColumn(name = "id_feedback"),
            inverseJoinColumns = @JoinColumn(name = "id_category")
    )
    private List<Category> categoryList;

    @ManyToOne
    @JoinColumn(name = "id_course")
    private Course course;

    public Feedback() {
    }

    public Feedback(long idFeedback, String descriptionName, List<Category> categoryList, Course course) {
        this.idFeedback = idFeedback;
        this.descriptionName = descriptionName;
        this.categoryList = categoryList;
        this.course = course;
    }

    public long getIdFeedback() {
        return idFeedback;
    }

    public void setIdFeedback(long idFeedback) {
        this.idFeedback = idFeedback;
    }

    public String getDescriptionName() {
        return descriptionName;
    }

    public void setDescriptionName(String descriptionName) {
        this.descriptionName = descriptionName;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
