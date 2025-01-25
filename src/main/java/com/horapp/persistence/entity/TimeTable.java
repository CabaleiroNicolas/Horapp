package com.horapp.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "time_tables")
public class TimeTable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "time_table_seq_gen")
    @SequenceGenerator(name = "time_table_seq_gen", sequenceName = "time_tables_id_seq", allocationSize = 1)
    private Long idTimeTable;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "timeTable")
    private List<Course> courseList;

    private boolean deleted;

    public TimeTable() {
    }

    public TimeTable(long idTimeTable, User user, List<Course> courseList) {
        this.idTimeTable = idTimeTable;
        this.user = user;
        this.courseList = courseList;
    }

    public TimeTable(long idTimeTable) {
        this.idTimeTable = idTimeTable;
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
