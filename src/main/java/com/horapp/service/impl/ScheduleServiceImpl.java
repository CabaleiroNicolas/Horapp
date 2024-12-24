package com.horapp.service.impl;

import com.horapp.exception.Schedule.ScheduleCreationException;
import com.horapp.exception.Schedule.ScheduleNotFoundException;
import com.horapp.exception.course.CourseNotFoundException;
import com.horapp.persistence.entity.Course;
import com.horapp.persistence.entity.Schedule;
import com.horapp.persistence.repository.ScheduleRepository;
import com.horapp.presentation.dto.request.ScheduleRequestDTO;
import com.horapp.presentation.dto.response.ScheduleResponseDTO;
import com.horapp.service.CourseService;
import com.horapp.service.ScheduleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

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
                            ScheduleResponseDTO scheduleResponseDTO = getScheduleResponseDTO(schedule, modelMapper);
                            return scheduleResponseDTO;
                }
                )
                .collect(Collectors.toList());
    }


    @Override
    public ScheduleResponseDTO findById(Long id) {
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(id);
        if(!optionalSchedule.isPresent()){
            throw new ScheduleNotFoundException(id);
        }
            ModelMapper modelMapper = new ModelMapper();
            Schedule schedule = optionalSchedule.get();
            ScheduleResponseDTO scheduleResponseDTO = getScheduleResponseDTO(schedule, modelMapper);
            return scheduleResponseDTO;
    }

    @Override
    public Schedule findEntityById(Long id) {
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(id);
        if(!optionalSchedule.isPresent()){
            throw new ScheduleNotFoundException(id);
        }
        return optionalSchedule.get();
    }
    @Override
    public ScheduleResponseDTO save(ScheduleRequestDTO scheduleRequestDTO) {
        try{
            Course course = courseService.findEntityById(scheduleRequestDTO.getIdCourse());
            ModelMapper modelMapper = new ModelMapper();
            Schedule schedule = new Schedule();
            schedule.setCourse(course);
            schedule.setCourseGroup(scheduleRequestDTO.getCourseGroup());
            scheduleRepository.save(schedule);
            return modelMapper.map(schedule, ScheduleResponseDTO.class);
        }catch (CourseNotFoundException | ScheduleCreationException e){
            throw new ScheduleCreationException(e.getMessage(), e);
        }catch (DataIntegrityViolationException e){
            throw new ScheduleCreationException("Data integrity violation while creating the schedule: " + e.getMessage(), e);
        }catch (Exception e){
            throw new ScheduleCreationException("An unexpected error occurred while creating the schedule.", e);
        }
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
