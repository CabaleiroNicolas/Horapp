package com.horapp.presentation.controller;

import com.horapp.presentation.dto.DayAndTimeRequestDTO;
import com.horapp.presentation.dto.DayAndTimeResponseDTO;
import com.horapp.service.DayAndTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/daysAndTimes")
public class DayAndTimeController {

    @Autowired
    private DayAndTimeService dayAndTimeService;

    @GetMapping
    public ResponseEntity<List<DayAndTimeResponseDTO>> findAll(){
        return new ResponseEntity<>(dayAndTimeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DayAndTimeResponseDTO> findById(@PathVariable Long id){
        return new ResponseEntity<>(dayAndTimeService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DayAndTimeResponseDTO> save(@RequestBody DayAndTimeRequestDTO dayAndTimeRequestDTO){
        return new ResponseEntity<>(dayAndTimeService.save(dayAndTimeRequestDTO), HttpStatus.CREATED);
    }
}
