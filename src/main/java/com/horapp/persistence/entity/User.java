package com.horapp.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
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

    @OneToOne(mappedBy = "user")
    private Major major;

    @OneToMany(mappedBy = "user")
    private List<Course> coursesList;

    @OneToMany(mappedBy = "user")
    private List<TimeTable> timeTablesList;

    public User() {
    }

    public User(long idUser, String username, String name, String lastname, String email, Major major, List<Course> coursesList, List<TimeTable> timeTablesList) {
        this.idUser = idUser;
        this.username = username;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.major = major;
        this.coursesList = coursesList;
        this.timeTablesList = timeTablesList;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public List<Course> getCoursesList() {
        return coursesList;
    }

    public void setCoursesList(List<Course> coursesList) {
        this.coursesList = coursesList;
    }

    public List<TimeTable> getTimeTablesList() {
        return timeTablesList;
    }

    public void setTimeTablesList(List<TimeTable> timeTablesList) {
        this.timeTablesList = timeTablesList;
    }
}
