package com.horapp.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_seq_gen")
    @SequenceGenerator(name = "course_seq_gen", sequenceName = "courses_id_seq", allocationSize = 1)
    private Long idCourse;

    private String courseName;

    private Boolean deleted;

    @OneToMany(fetch = FetchType.LAZY , mappedBy = "course")
    private List<Schedule> scheduleList;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_time_table")
    private TimeTable timeTable;

    @ManyToOne
    @JoinColumn(name = "id_major")
    private Major major;

    @OneToMany(fetch = FetchType.LAZY ,mappedBy = "course")
    private List<Feedback> feedbacks;

    public Course() {
    }

    public Course(long idCourse) {
        this.idCourse = idCourse;
    }

    public Course(String courseName, Major major, TimeTable timeTable, User user) {
        this.courseName = courseName;
        this.major = major;
        this.timeTable = timeTable;
        this.user = user;
        this.deleted = false;
    }

    public Course(Long idCourse, String courseName, List<Schedule> scheduleList, User user, TimeTable timeTable, Major major, List<Feedback> feedbacks) {
        this.idCourse = idCourse;
        this.courseName = courseName;
        this.scheduleList = scheduleList;
        this.user = user;
        this.timeTable = timeTable;
        this.major = major;
        this.feedbacks = feedbacks;
    }

    public long getIdCourse() {
        return idCourse;
    }

    public String getCourseName() {
        return courseName;
    }

    public List<Schedule> getScheduleList() {
        return scheduleList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TimeTable getTimeTable() {
        return timeTable;
    }

    public Major getMajor() {
        return major;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

}
