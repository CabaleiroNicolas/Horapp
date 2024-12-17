package com.horapp.presentation.controller;

import com.horapp.persistence.entity.Major;
import com.horapp.presentation.dto.MajorDTO;
import com.horapp.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("majors")
public class MajorController {

    @Autowired
    private MajorService majorService;

    @GetMapping
    public ResponseEntity<List<MajorDTO>> findAll(){
        return new ResponseEntity<>(majorService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<MajorDTO> findById(@PathVariable Long id){
        return new ResponseEntity<>(majorService.findById(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<MajorDTO> saveMajor(@RequestBody MajorDTO majorDTO){
        return new ResponseEntity<>(majorService.saveMajor(majorDTO), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        return new ResponseEntity<>(majorService.deleteById(id), HttpStatus.NO_CONTENT);
    }
}
