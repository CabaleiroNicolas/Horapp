package com.horapp.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "majors")
public class Major {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "major_seq_gen")
    @SequenceGenerator(name = "major_seq_gen", sequenceName = "majors_id_seq", allocationSize = 1)
    private long idMajor;

    private String majorName;

    private boolean deleted;

    //Revisar relacion onetoone?
    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;

    @OneToMany(fetch = FetchType.EAGER , mappedBy = "major")
    private List<Course> courseList;

    public Major() {
    }

    public Major(long idMajor, String majorName, boolean deleted, User user, List<Course> courseList) {
        this.idMajor = idMajor;
        this.majorName = majorName;
        this.deleted = deleted;
        this.user = user;
        this.courseList = courseList;
    }

    public long getIdMajor() {
        return idMajor;
    }

    public void setIdMajor(long idMajor) {
        this.idMajor = idMajor;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }
}
