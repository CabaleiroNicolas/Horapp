package com.horapp.presentation.controller;

import com.horapp.presentation.dto.request.DayAndTimeRequestDTO;
import com.horapp.presentation.dto.response.DayAndTimeResponseDTO;
import com.horapp.service.DayAndTimeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/daysAndTimes")
public class DayAndTimeController {


    private final DayAndTimeService dayAndTimeService;

    public DayAndTimeController(DayAndTimeService dayAndTimeService) {
        this.dayAndTimeService = dayAndTimeService;
    }

    @GetMapping("/{dayAndTimeId}")
    public ResponseEntity<DayAndTimeResponseDTO> findById(@PathVariable Long dayAndTimeId){
        return new ResponseEntity<>(dayAndTimeService.findById(dayAndTimeId), HttpStatus.OK);
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<List<DayAndTimeResponseDTO>> findAllBySchedule(@PathVariable Long scheduleId){
        return new ResponseEntity<>(dayAndTimeService.findAllBySchedule(scheduleId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DayAndTimeResponseDTO> save(@Valid @RequestBody DayAndTimeRequestDTO dayAndTimeRequestDTO){
        return new ResponseEntity<>(dayAndTimeService.save(dayAndTimeRequestDTO), HttpStatus.CREATED);
    }
}
