package com.horapp.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_seq_gen")
    @SequenceGenerator(name = "course_seq_gen", sequenceName = "courses_id_seq", allocationSize = 1)
    private long idCourse;

    private String courseName;

    private boolean deleted;

    @OneToMany(fetch = FetchType.EAGER , mappedBy = "course")
    private List<Schedule> scheduleList;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_time_table")
    private TimeTable timeTable;

    @ManyToOne
    @JoinColumn(name = "id_major", nullable = false)
    private Major major;

    @OneToMany(fetch = FetchType.EAGER ,mappedBy = "course")
    private List<Feedback> feedbacks;

    public Course() {
    }

    public Course(long idCourse, String courseName, List<Schedule> scheduleList, User user, TimeTable timeTable, Major major, List<Feedback> feedbacks) {
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

    public void setIdCourse(long idCourse) {
        this.idCourse = idCourse;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<Schedule> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
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

    public void setTimeTable(TimeTable timeTable) {
        this.timeTable = timeTable;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Course{" +
                "idCourse=" + idCourse +
                ", courseName='" + courseName + '\'' +
                ", deleted=" + deleted +
                ", scheduleList=" + scheduleList +
                ", user=" + user +
                ", timeTable=" + timeTable +
                ", major=" + major +
                ", feedbacks=" + feedbacks +
                '}';
    }
}
