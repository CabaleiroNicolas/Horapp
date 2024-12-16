package com.horapp.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_schedule")
    private long idSchedule;
    @Column(name = "course_group")
    private String courseGroup;

    @OneToMany(mappedBy = "schedule")
    //@ElementCollection(fetch = FetchType.EAGER)
    private List<DayAndTime> daysAndTimes;

    @ManyToOne
    @JoinColumn(name = "id_course", nullable = false)
    private Course course;

}
