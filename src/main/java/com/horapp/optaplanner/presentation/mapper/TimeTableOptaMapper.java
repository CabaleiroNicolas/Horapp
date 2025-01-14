package com.horapp.optaplanner.presentation.mapper;

import com.horapp.optaplanner.modeldomainOP.CourseOptaPlanner;
import com.horapp.optaplanner.modeldomainOP.DayAndTimeOptaPlanner;
import com.horapp.optaplanner.modeldomainOP.ScheduleOptaPlanner;
import com.horapp.optaplanner.modeldomainOP.TimeTableOptaPlanner;
import com.horapp.persistence.entity.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TimeTableOptaMapper {
    public static TimeTableOptaPlanner mapToTimeTableOptaPlanner(List<Course> courses) {
        TimeTableOptaPlanner timeTableOptaPlanner = new TimeTableOptaPlanner();

        List<CourseOptaPlanner> courseOptaList = courses.stream()
                .map(course -> {
                    CourseOptaPlanner courseOpta = new CourseOptaPlanner();
                    courseOpta.setId(course.getIdCourse());
                    courseOpta.setCourseName(course.getCourseName());

                    // Mapear los horarios disponibles
                    List<ScheduleOptaPlanner> scheduleOptaList = course.getScheduleList().stream()
                            .filter(schedule -> (schedule.getDaysAndTimes() != null && !schedule.getDaysAndTimes().isEmpty()))
                            .map(schedule -> {

                                ScheduleOptaPlanner scheduleOpta = new ScheduleOptaPlanner();

                                scheduleOpta.setCourseGroup(schedule.getCourseGroup());

                                // Mapear los días y horas
                                List<DayAndTimeOptaPlanner> dayAndTimeOptaList = schedule.getDaysAndTimes().stream()
                                        .map(dayAndTime -> {
                                            DayAndTimeOptaPlanner dayAndTimeOpta = new DayAndTimeOptaPlanner();
                                            dayAndTimeOpta.setDay(dayAndTime.getDay());
                                            dayAndTimeOpta.setStartTime(dayAndTime.getStartTime());
                                            dayAndTimeOpta.setEndTime(dayAndTime.getEndTime());
                                            return dayAndTimeOpta;
                                        })
                                        .collect(Collectors.toList());

                                scheduleOpta.setDayAndTimes(new ArrayList<>(dayAndTimeOptaList));
                                return scheduleOpta;
                            })
                            .filter(Objects::nonNull) // Eliminar nulos resultantes del filtro
                            .collect(Collectors.toList());

                    courseOpta.setAvailableSchedules(scheduleOptaList);
                    return courseOpta;
                })
                .collect(Collectors.toList());

        timeTableOptaPlanner.setCourses(new ArrayList<>(courseOptaList));
        return timeTableOptaPlanner;
    }
}
