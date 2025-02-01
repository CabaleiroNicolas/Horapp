package com.horapp.presentation.controller;

import com.horapp.presentation.dto.request.MajorRequestDTO;
import com.horapp.presentation.dto.response.MajorResponseDTO;
import com.horapp.service.MajorService;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<MajorResponseDTO>> findAll(){
        return new ResponseEntity<>(majorService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/findAll/{id}")
    public ResponseEntity<List<MajorResponseDTO>> findAllByUser(@PathVariable Long id){
        return new ResponseEntity<>(majorService.findAllByUser(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MajorResponseDTO> findById(@PathVariable Long id){
        return new ResponseEntity<>(majorService.findById(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<MajorResponseDTO> saveMajor(@Valid @RequestBody MajorRequestDTO majorRequestDTO){
        return new ResponseEntity<>(majorService.saveMajor(majorRequestDTO), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        return new ResponseEntity<>(majorService.deleteById(id), HttpStatus.NO_CONTENT);
    }
}
