package com.horapp.presentation.controller;

import com.horapp.presentation.dto.request.FeedbackRequestDTO;
import com.horapp.presentation.dto.response.FeedbackResponseDTO;
import com.horapp.service.FeedbackService;
import jakarta.validation.Valid;
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

    @PostMapping
    public ResponseEntity<FeedbackResponseDTO> save(@Valid @RequestBody FeedbackRequestDTO feedbackRequestDTO){
        return new ResponseEntity<>(feedbackService.save(feedbackRequestDTO), HttpStatus.CREATED);
    }

}
