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
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category")
    private long idCategory;
    @Column(name = "category_name")
    private String majorName;
    @Column(name = "description_name")
    private String descriptionName;

    @ManyToMany(mappedBy = "categoryList")
    private List<Feedback> feedbackList;
}
