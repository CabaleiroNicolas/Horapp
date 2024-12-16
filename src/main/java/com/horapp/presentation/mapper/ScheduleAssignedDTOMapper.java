package com.horapp.presentation.mapper;

import com.horapp.domain.TimeTableOptaPlanner;
import com.horapp.presentation.dto.ScheduleAssignedDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ScheduleAssignedDTOMapper {
    public static List<ScheduleAssignedDTO> toScheduleAssignedDTOList(TimeTableOptaPlanner solvedTimeTable){
        List<ScheduleAssignedDTO> assignedCoursesList = new ArrayList<>();
        solvedTimeTable.getCourses().forEach(
                c -> {
                    ScheduleAssignedDTO scheduleAssignedDTO = new ScheduleAssignedDTO();
                    scheduleAssignedDTO.setCourseName(c.getCourseName());
                    scheduleAssignedDTO.setCourseGroup(c.getAssignedSchedule().getCourseGroup());
                    String days = c.getAssignedSchedule().getDayAndTimes().stream()
                            .map(dayAndTime -> dayAndTime.getDay().toString())
                            .collect(Collectors.joining(", "));
                    scheduleAssignedDTO.setDay(days);
                    String hours = c.getAssignedSchedule().getDayAndTimes().stream()
                            .map(dayAndTime -> dayAndTime.getStartTime().toString().concat("-").concat(dayAndTime.getEndTime().toString()))
                            .collect(Collectors.joining(", "));
                    scheduleAssignedDTO.setHour(hours);
                    assignedCoursesList.add(scheduleAssignedDTO);
                }
        );
        return assignedCoursesList;
    }
}
