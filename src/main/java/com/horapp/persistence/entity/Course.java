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
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_course")
    private long idCourse;
    @Column(name = "course_name")
    private String courseName;

    @OneToMany(mappedBy = "course")
    //@ElementCollection(fetch = FetchType.EAGER)
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

    @OneToMany(mappedBy = "course")
    private List<Feedback> feedbacks;
}
