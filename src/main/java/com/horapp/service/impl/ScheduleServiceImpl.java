package com.horapp.service.impl;

import com.horapp.persistence.entity.Course;
import com.horapp.persistence.entity.Schedule;
import com.horapp.persistence.repository.ScheduleRepository;
import com.horapp.presentation.dto.request.ScheduleRequestDTO;
import com.horapp.presentation.dto.response.ScheduleResponseDTO;
import com.horapp.service.CourseService;
import com.horapp.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private CourseService courseService;

    @Override
    public List<ScheduleResponseDTO> findAll() {
        return scheduleRepository.findAll().stream()
                .map(this::getScheduleResponseDTO)
                .collect(Collectors.toList());
    }


    @Override
    public ScheduleResponseDTO findById(Long id) {
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(id);
        if(optionalSchedule.isEmpty()){
            throw new NotFoundException("Schedule not found with Id = " + id);
        }
        return getScheduleResponseDTO(optionalSchedule.get());
    }


    @Override
    public ScheduleResponseDTO save(ScheduleRequestDTO scheduleRequestDTO) {
            Course course = new Course(scheduleRequestDTO.idCourse());
            Schedule schedule = new Schedule(scheduleRequestDTO.courseGroup(), course);
            scheduleRepository.save(schedule);
            return getScheduleResponseDTO(schedule);
    }

    private ScheduleResponseDTO getScheduleResponseDTO(Schedule schedule) {

        List<String> days = schedule.getDaysAndTimes() != null ? schedule.getDaysAndTimes().stream()
                .map(day -> day.getDay().toString())
                .collect(Collectors.toList()) : null;

        return new ScheduleResponseDTO(schedule.getIdSchedule(),
                schedule.getCourseGroup(),
                days);
    }
}
