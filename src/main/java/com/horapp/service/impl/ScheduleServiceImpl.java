package com.horapp.service.impl;

import com.horapp.persistence.entity.Course;
import com.horapp.persistence.entity.DayAndTime;
import com.horapp.persistence.entity.Schedule;
import com.horapp.persistence.repository.ScheduleRepository;
import com.horapp.presentation.dto.ScheduleRequestDTO;
import com.horapp.presentation.dto.ScheduleResponseDTO;
import com.horapp.service.CourseService;
import com.horapp.service.ScheduleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    private static ScheduleResponseDTO getScheduleResponseDTO(Schedule schedule, ModelMapper modelMapper) {
        ScheduleResponseDTO scheduleResponseDTO = modelMapper.map(schedule, ScheduleResponseDTO.class);
        List<String> days = schedule.getDaysAndTimes().stream()
                .map(day -> day.getDay().toString())
                .collect(Collectors.toList());
        scheduleResponseDTO.setDays(days);
        return scheduleResponseDTO;
    }

    @Override
    public ScheduleResponseDTO findById(Long id) {
        try{
            Optional<Schedule> optionalSchedule = scheduleRepository.findById(id);
            if(optionalSchedule.isPresent()){
                ModelMapper modelMapper = new ModelMapper();
                Schedule schedule = optionalSchedule.get();
                ScheduleResponseDTO scheduleResponseDTO = getScheduleResponseDTO(schedule, modelMapper);
                return scheduleResponseDTO;
            }
        } catch (Exception e) {
            System.err.println("Schedule with ID: " + e.getMessage() + "don´t exists in database");
        }
        return new ScheduleResponseDTO();
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
        }catch (Exception e){
            throw new RuntimeException("Something goes wrong creating the schedule: " + e.getMessage());
        }
    }

    @Override
    public Schedule findEntityById(Long id) {
        try{
            Optional<Schedule> optionalSchedule = scheduleRepository.findById(id);
            if(optionalSchedule.isPresent()){
                ModelMapper modelMapper = new ModelMapper();
                Schedule schedule = optionalSchedule.get();
                return schedule;
            }
        } catch (Exception e) {
            System.err.println("Schedule with ID: " + e.getMessage() + "don´t exists in database");
        }
        return null;
    }

}
