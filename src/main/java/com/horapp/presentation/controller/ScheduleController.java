package com.horapp.presentation.controller;

import com.horapp.presentation.dto.request.ScheduleRequestDTO;
import com.horapp.presentation.dto.response.ScheduleResponseDTO;
import com.horapp.service.ScheduleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<List<ScheduleResponseDTO>> findAllByCourse(@PathVariable Long courseId){
        return new ResponseEntity<>(scheduleService.findAllByCourse(courseId), HttpStatus.OK);
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDTO> findById(@PathVariable Long scheduleId){
        return new ResponseEntity<>(scheduleService.findById(scheduleId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody ScheduleRequestDTO scheduleRequestDTO){
        return new ResponseEntity<>(scheduleService.save(scheduleRequestDTO), HttpStatus.CREATED);
    }


}
