package com.horapp.presentation.controller;

import com.horapp.presentation.dto.request.UserRequestDTO;
import com.horapp.presentation.dto.response.UserResponseDTO;
import com.horapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id){
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> save(@Valid @RequestBody UserRequestDTO userRequestDTO){
        return new ResponseEntity<>(userService.save(userRequestDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        return new ResponseEntity<>(userService.deleteById(id), HttpStatus.NO_CONTENT);
    }
}
