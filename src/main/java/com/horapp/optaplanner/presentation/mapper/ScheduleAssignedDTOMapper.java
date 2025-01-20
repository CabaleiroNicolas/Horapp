package com.horapp.optaplanner.presentation.mapper;
import com.horapp.presentation.dto.response.ScheduleAssignedDTO;
import com.horapp.optaplanner.modeldomainOP.TimeTableOptaPlanner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ScheduleAssignedDTOMapper {
    public static List<ScheduleAssignedDTO> toScheduleAssignedDTOList(TimeTableOptaPlanner solvedTimeTable) {
        List<ScheduleAssignedDTO> assignedCoursesList = new ArrayList<>();
        solvedTimeTable.getCourses().forEach(
                c -> {
                    String courseName = c.getCourseName();
                    String courseGroup = c.getAssignedSchedule().getCourseGroup();
                    String days = c.getAssignedSchedule().getDayAndTimes().stream()
                            .map(dayAndTime -> dayAndTime.getDay().toString())
                            .collect(Collectors.joining(", "));
                    String hours = c.getAssignedSchedule().getDayAndTimes().stream()
                            .map(dayAndTime -> dayAndTime.getStartTime().toString().concat("-").concat(dayAndTime.getEndTime().toString()))
                            .collect(Collectors.joining(", "));

                    assignedCoursesList.add(new ScheduleAssignedDTO(courseName, courseGroup, days, hours));
                }
        );
        return assignedCoursesList;
    }
}
