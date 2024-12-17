package com.horapp.persistence.entity;

import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Table(name = "day_and_time")
public class DayAndTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_day_and_time")
    private long idDayAndTime;
    @Enumerated(EnumType.STRING)
    private DayOfWeek day;
    @Column(name = "start_time", columnDefinition = "TIME")
    private LocalTime startTime;
    @Column(name = "end_time", columnDefinition = "TIME")
    private LocalTime endTime;
    @ManyToOne
    @JoinColumn(name = "id_schedule", nullable = false)
    private Schedule schedule;

    public DayAndTime() {
    }

    public DayAndTime(long idDayAndTime, DayOfWeek day, LocalTime startTime, LocalTime endTime, Schedule schedule) {
        this.idDayAndTime = idDayAndTime;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.schedule = schedule;
    }

    public long getIdDayAndTime() {
        return idDayAndTime;
    }

    public void setIdDayAndTime(long idDayAndTime) {
        this.idDayAndTime = idDayAndTime;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}

