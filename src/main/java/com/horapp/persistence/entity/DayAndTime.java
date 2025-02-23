package com.horapp.persistence.entity;

import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Table(name = "days_and_times")
public class DayAndTime {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "day_and_time_seq_gen")
    @SequenceGenerator(name = "day_and_time_seq_gen", sequenceName = "days_and_times_id_seq", allocationSize = 1)
    private long idDayAndTime;
    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week")
    private DayOfWeek day;

    private LocalTime startTime;

    private LocalTime endTime;

    //Posiblemente necesite @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_schedule", nullable = false)
    private Schedule schedule;

    public DayAndTime() {
    }

    public DayAndTime(DayOfWeek day, LocalTime startTime, LocalTime endTime, Schedule schedule) {
        this.day = day;
        this.endTime = endTime;
        this.schedule = schedule;
        this.startTime = startTime;
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

