package com.horapp.presentation.controller;

import com.horapp.presentation.dto.request.UserRequestDTO;
import com.horapp.presentation.dto.response.UserResponseDTO;
import com.horapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long userId){
        return new ResponseEntity<>(userService.findById(userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> save(@Valid @RequestBody UserRequestDTO userRequestDTO){
        return new ResponseEntity<>(userService.save(userRequestDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteById(@PathVariable Long userId){
        return new ResponseEntity<>(userService.deleteById(userId), HttpStatus.NO_CONTENT);
    }
}
