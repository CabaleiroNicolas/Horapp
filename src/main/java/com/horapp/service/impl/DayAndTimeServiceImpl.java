package com.horapp.service.impl;

import com.horapp.persistence.entity.DayAndTime;
import com.horapp.persistence.entity.Schedule;
import com.horapp.persistence.repository.DayAndTimeRepository;
import com.horapp.presentation.dto.request.DayAndTimeRequestDTO;
import com.horapp.presentation.dto.response.DayAndTimeResponseDTO;
import com.horapp.service.DayAndTimeService;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DayAndTimeServiceImpl implements DayAndTimeService {

    private final DayAndTimeRepository dayAndTimeRepository;


    public DayAndTimeServiceImpl(DayAndTimeRepository dayAndTimeRepository) {
        this.dayAndTimeRepository = dayAndTimeRepository;
    }

    @Override
    public List<DayAndTimeResponseDTO> findAllBySchedule(Long id) {
        return dayAndTimeRepository.findAllBySchedule_IdSchedule(id).stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DayAndTimeResponseDTO findById(Long id) {
        Optional<DayAndTime> optionalDayAndTime = dayAndTimeRepository.findById(id);
        if(optionalDayAndTime.isEmpty()){
            throw new NotFoundException("DayAndTime not found with Id = " + id);
        }
        DayAndTime dayAndTime = optionalDayAndTime.get();
        return convertToResponseDTO(dayAndTime);
    }

    @Override
    public DayAndTimeResponseDTO save(DayAndTimeRequestDTO dayAndTimeRequestDTO) {

        Schedule scheduleAssigned = new Schedule(dayAndTimeRequestDTO.idSchedule());

        DayAndTime dayAndTime = new DayAndTime(
                DayOfWeek.valueOf(dayAndTimeRequestDTO.day().toUpperCase()),
                LocalTime.parse(dayAndTimeRequestDTO.startTime(), DateTimeFormatter.ofPattern("HH:mm")),
                LocalTime.parse(dayAndTimeRequestDTO.endTime(), DateTimeFormatter.ofPattern("HH:mm")),
                scheduleAssigned
        );

        dayAndTimeRepository.save(dayAndTime);
        return convertToResponseDTO(dayAndTime);
    }

    private DayAndTimeResponseDTO convertToResponseDTO(DayAndTime dayAndTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return new DayAndTimeResponseDTO(dayAndTime.getIdDayAndTime(), dayAndTime.getDay().toString(), dayAndTime.getStartTime().format(formatter)
                + " - "
                + dayAndTime.getEndTime().format(formatter));
    }
}
