package com.horapp.presentation.controller;

import com.horapp.presentation.dto.request.ScheduleRequestDTO;
import com.horapp.presentation.dto.response.ScheduleResponseDTO;
import com.horapp.service.ScheduleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/findAll/{id}")
    public ResponseEntity<List<ScheduleResponseDTO>> findAllByCourse(@PathVariable Long id){
        return new ResponseEntity<>(scheduleService.findAllByCourse(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDTO> findById(@PathVariable Long id){
        return new ResponseEntity<>(scheduleService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody ScheduleRequestDTO scheduleRequestDTO){
        return new ResponseEntity<>(scheduleService.save(scheduleRequestDTO), HttpStatus.CREATED);
    }


}
