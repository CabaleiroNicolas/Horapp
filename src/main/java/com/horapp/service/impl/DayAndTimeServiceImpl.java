package com.horapp.service.impl;

import com.horapp.exception.schedule.ScheduleNotFoundException;
import com.horapp.exception.dayAndTime.DayAndTimeCreationException;
import com.horapp.exception.dayAndTime.DayAndTimeNotFoundException;
import com.horapp.persistence.entity.DayAndTime;
import com.horapp.persistence.entity.Schedule;
import com.horapp.persistence.repository.DayAndTimeRepository;
import com.horapp.presentation.dto.request.DayAndTimeRequestDTO;
import com.horapp.presentation.dto.response.DayAndTimeResponseDTO;
import com.horapp.service.DayAndTimeService;
import com.horapp.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DayAndTimeServiceImpl implements DayAndTimeService {

    @Autowired
    private DayAndTimeRepository dayAndTimeRepository;

    @Autowired
    private ScheduleService scheduleService;

    @Override
    public List<DayAndTimeResponseDTO> findAll() {
        return dayAndTimeRepository.findAll().stream()
                .map(dayAndTime -> convertToResponseDTO(dayAndTime))
                .collect(Collectors.toList());
    }

    @Override
    public DayAndTimeResponseDTO findById(Long id) {
        Optional<DayAndTime> optionalDayAndTime = dayAndTimeRepository.findById(id);
        if(!optionalDayAndTime.isPresent()){
            throw new DayAndTimeNotFoundException(id);
        }
        DayAndTime dayAndTime = optionalDayAndTime.get();
        return convertToResponseDTO(dayAndTime);
    }

    @Override
    public DayAndTimeResponseDTO save(DayAndTimeRequestDTO dayAndTimeRequestDTO) {
        try {
            Schedule schedule = scheduleService.findEntityById(dayAndTimeRequestDTO.getIdSchedule());
            DayAndTime dayAndTime = new DayAndTime();
            dayAndTime.setDay(DayOfWeek.valueOf(dayAndTimeRequestDTO.getDay().toUpperCase()));
            dayAndTime.setStartTime(LocalTime.parse(dayAndTimeRequestDTO.getStartTime(), DateTimeFormatter.ofPattern("HH:mm")));
            dayAndTime.setEndTime(LocalTime.parse(dayAndTimeRequestDTO.getEndTime(), DateTimeFormatter.ofPattern("HH:mm")));
            dayAndTime.setSchedule(schedule);
            dayAndTimeRepository.save(dayAndTime);
            return convertToResponseDTO(dayAndTime);
        } catch (ScheduleNotFoundException | DayAndTimeNotFoundException e){
            throw new DayAndTimeCreationException(e.getMessage(), e);
        } catch (DataIntegrityViolationException e){
            throw new DayAndTimeCreationException("Data integrity violation while creating the dayAndTime: " + e.getMessage(), e);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    private DayAndTimeResponseDTO convertToResponseDTO(DayAndTime dayAndTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        DayAndTimeResponseDTO dto = new DayAndTimeResponseDTO();
        dto.setIdDayAndTime(dayAndTime.getIdDayAndTime());
        dto.setDay(dayAndTime.getDay().toString());
        dto.setTime(dayAndTime.getStartTime().format(formatter)
                + " - "
                + dayAndTime.getEndTime().format(formatter));
        return dto;
    }
}
