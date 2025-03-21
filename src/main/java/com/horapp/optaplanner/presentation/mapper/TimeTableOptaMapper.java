package com.horapp.optaplanner.presentation.mapper;

import com.horapp.optaplanner.modeldomainOP.CourseOptaPlanner;
import com.horapp.optaplanner.modeldomainOP.DayAndTimeOptaPlanner;
import com.horapp.optaplanner.modeldomainOP.ScheduleOptaPlanner;
import com.horapp.optaplanner.modeldomainOP.TimeTableOptaPlanner;
import com.horapp.persistence.entity.Course;

import java.util.List;
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
                                scheduleOpta.setId(schedule.getIdSchedule());
                                scheduleOpta.setCourse(courseOpta);

                                // Mapear los días y horas
                                List<DayAndTimeOptaPlanner> dayAndTimeOptaList = schedule.getDaysAndTimes().stream()
                                        .map(dayAndTime -> new DayAndTimeOptaPlanner(
                                               dayAndTime.getDay(),
                                               dayAndTime.getStartTime(),
                                               dayAndTime.getEndTime()
                                       )).toList();

                                scheduleOpta.setDayAndTimes(List.copyOf(dayAndTimeOptaList));
                                return scheduleOpta;
                            })
                            .toList();

                    courseOpta.setAvailableSchedules(scheduleOptaList);
                    return courseOpta;
                })
                .toList();

        // Recolectar todos los DayAndTime de todos los Schedule
        List<DayAndTimeOptaPlanner> allDayAndTimes = courseOptaList.stream()
                .flatMap(course -> course.getAvailableSchedules().stream())
                .flatMap(schedule -> schedule.getDayAndTimes().stream())
                .distinct()
                .toList();

        timeTableOptaPlanner.setAllDayAndTimes(List.copyOf(allDayAndTimes));
        timeTableOptaPlanner.setCourses(List.copyOf(courseOptaList));
        return timeTableOptaPlanner;
    }
}
