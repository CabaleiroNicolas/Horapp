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
@Table(name = "user")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private long idUser;
    private String username;
    private String name;
    private String lastname;
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Major> majorList;

    @OneToMany(mappedBy = "user")
    private List<Course> coursesList;

    @OneToMany(mappedBy = "user")
    private List<TimeTable> timeTablesList;

}
