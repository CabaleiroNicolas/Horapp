package com.horapp.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
}
