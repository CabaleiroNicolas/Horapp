package com.horapp.presentation.controller;

import com.horapp.presentation.dto.request.FeedbackRequestDTO;
import com.horapp.presentation.dto.response.FeedbackResponseDTO;
import com.horapp.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping
    public ResponseEntity<List<FeedbackResponseDTO>> findAll(){
        return new ResponseEntity<>(feedbackService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackResponseDTO> findById(@PathVariable Long id){
        return new ResponseEntity<>(feedbackService.findById(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FeedbackResponseDTO> save(@RequestBody FeedbackRequestDTO feedbackRequestDTO){
        return new ResponseEntity<>(feedbackService.save(feedbackRequestDTO), HttpStatus.CREATED);
    }

}
