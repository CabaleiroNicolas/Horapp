package com.horapp.presentation.controller;

import com.horapp.presentation.dto.request.TimeTableRequestDTO;
import com.horapp.presentation.dto.response.TimeTableResponseDTO;
import com.horapp.service.TimeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/timetables")
public class TimeTableController {

    @Autowired
    private TimeTableService timeTableService;

    @GetMapping
    public ResponseEntity<List<TimeTableResponseDTO>> findAll(){
        return  new ResponseEntity<>(timeTableService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimeTableResponseDTO> findById(@PathVariable Long id){
        return new ResponseEntity<>(timeTableService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TimeTableResponseDTO> save(@RequestBody TimeTableRequestDTO timeTableRequestDTO){
        return new ResponseEntity<>(timeTableService.save(timeTableRequestDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        return new ResponseEntity<>(timeTableService.deleteById(id), HttpStatus.OK);
    }
}
