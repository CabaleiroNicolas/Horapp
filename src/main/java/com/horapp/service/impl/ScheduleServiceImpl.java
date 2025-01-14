package com.horapp.service.impl;

import com.horapp.persistence.entity.Course;
import com.horapp.persistence.entity.Schedule;
import com.horapp.persistence.repository.ScheduleRepository;
import com.horapp.presentation.dto.request.ScheduleRequestDTO;
import com.horapp.presentation.dto.response.ScheduleResponseDTO;
import com.horapp.service.CourseService;
import com.horapp.service.ScheduleService;
import org.modelmapper.ModelMapper;
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
        ModelMapper modelMapper = new ModelMapper();
        return scheduleRepository.findAll().stream()
                .map(schedule -> {
                            return getScheduleResponseDTO(schedule, modelMapper);
                }
                )
                .collect(Collectors.toList());
    }


    @Override
    public ScheduleResponseDTO findById(Long id) {
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(id);
        if(optionalSchedule.isEmpty()){
            throw new NotFoundException("Schedule not found with Id = " + id);
        }
            ModelMapper modelMapper = new ModelMapper();
            Schedule schedule = optionalSchedule.get();
        return getScheduleResponseDTO(schedule, modelMapper);
    }

    @Override
    public Schedule findEntityById(Long id) {
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(id);
        if(optionalSchedule.isEmpty()){
            throw new NotFoundException("Schedule not found with Id = " + id);
        }
        return optionalSchedule.get();
    }
    @Override
    public ScheduleResponseDTO save(ScheduleRequestDTO scheduleRequestDTO) {
            Course course = courseService.findEntityById(scheduleRequestDTO.getIdCourse());
            ModelMapper modelMapper = new ModelMapper();
            Schedule schedule = new Schedule();
            schedule.setCourse(course);
            schedule.setCourseGroup(scheduleRequestDTO.getCourseGroup());
            scheduleRepository.save(schedule);
            return modelMapper.map(schedule, ScheduleResponseDTO.class);

    }


    private static ScheduleResponseDTO getScheduleResponseDTO(Schedule schedule, ModelMapper modelMapper) {
        ScheduleResponseDTO scheduleResponseDTO = modelMapper.map(schedule, ScheduleResponseDTO.class);
        List<String> days = schedule.getDaysAndTimes().stream()
                .map(day -> day.getDay().toString())
                .collect(Collectors.toList());
        scheduleResponseDTO.setDays(days);
        return scheduleResponseDTO;
    }
}
