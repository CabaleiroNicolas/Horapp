package com.horapp.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "schedules")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "schedule_seq_gen")
    @SequenceGenerator(name = "schedule_seq_gen", sequenceName = "schedules_id_seq", allocationSize = 1)
    private Long idSchedule;

    private String courseGroup;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "schedule")
    private List<DayAndTime> daysAndTimes;

    @ManyToOne
    @JoinColumn(name = "id_course")
    private Course course;

    public Schedule(Long idSchedule, String courseGroup, List<DayAndTime> daysAndTimes, Course course) {
        this.idSchedule = idSchedule;
        this.courseGroup = courseGroup;
        this.daysAndTimes = daysAndTimes;
        this.course = course;
    }

    public Schedule() {
    }

    public Schedule(Long idSchedule) {
        this.idSchedule = idSchedule;
    }

    public Schedule(String courseGroup, Course course) {
        this.courseGroup = courseGroup;
        this.course = course;
    }

    public long getIdSchedule() {
        return idSchedule;
    }

    public String getCourseGroup() {
        return courseGroup;
    }

    public List<DayAndTime> getDaysAndTimes() {
        return daysAndTimes;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

}
