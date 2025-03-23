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

    public long getIdDayAndTime() {
        return idDayAndTime;
    }
    public DayOfWeek getDay() {
        return day;
    }
    public LocalTime getStartTime() {
        return startTime;
    }
    public LocalTime getEndTime() {
        return endTime;
    }
    public Schedule getSchedule() {
        return schedule;
    }
    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}

