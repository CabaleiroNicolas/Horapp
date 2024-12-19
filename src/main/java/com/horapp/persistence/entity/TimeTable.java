package com.horapp.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "time_table")
public class TimeTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_time_table")
    private long idTimeTable;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @OneToMany(mappedBy = "timeTable")
    //@ElementCollection(fetch = FetchType.EAGER)
    private List<Course> courseList;

    private boolean deleted;

    public TimeTable() {
    }

    public TimeTable(long idTimeTable, User user, List<Course> courseList) {
        this.idTimeTable = idTimeTable;
        this.user = user;
        this.courseList = courseList;
    }

    public long getIdTimeTable() {
        return idTimeTable;
    }

    public void setIdTimeTable(long idTimeTable) {
        this.idTimeTable = idTimeTable;
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
