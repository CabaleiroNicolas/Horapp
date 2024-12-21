package com.horapp.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_schedule")
    private long idSchedule;
    @Column(name = "course_group")
    private String courseGroup;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "schedule")
    //@ElementCollection(fetch = FetchType.EAGER)
    private List<DayAndTime> daysAndTimes;

    @ManyToOne
    @JoinColumn(name = "id_course", nullable = false)
    private Course course;

    public Schedule(long idSchedule, String courseGroup, List<DayAndTime> daysAndTimes, Course course) {
        this.idSchedule = idSchedule;
        this.courseGroup = courseGroup;
        this.daysAndTimes = daysAndTimes;
        this.course = course;
    }

    public Schedule() {
    }

    public long getIdSchedule() {
        return idSchedule;
    }

    public void setIdSchedule(long idSchedule) {
        this.idSchedule = idSchedule;
    }

    public String getCourseGroup() {
        return courseGroup;
    }

    public void setCourseGroup(String courseGroup) {
        this.courseGroup = courseGroup;
    }

    public List<DayAndTime> getDaysAndTimes() {
        return daysAndTimes;
    }

    public void setDaysAndTimes(List<DayAndTime> daysAndTimes) {
        this.daysAndTimes = daysAndTimes;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "idSchedule=" + idSchedule +
                ", courseGroup='" + courseGroup + '\'' +
                ", daysAndTimes=" + daysAndTimes +
                '}';
    }
}
