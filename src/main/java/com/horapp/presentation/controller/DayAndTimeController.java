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

    @GetMapping("/{id}")
    public ResponseEntity<DayAndTimeResponseDTO> findById(@PathVariable Long id){
        return new ResponseEntity<>(dayAndTimeService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DayAndTimeResponseDTO> save(@Valid @RequestBody DayAndTimeRequestDTO dayAndTimeRequestDTO){
        return new ResponseEntity<>(dayAndTimeService.save(dayAndTimeRequestDTO), HttpStatus.CREATED);
    }
}
