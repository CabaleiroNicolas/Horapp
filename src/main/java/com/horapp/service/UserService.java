package com.horapp.service;

import com.horapp.persistence.entity.User;
import com.horapp.presentation.dto.request.UserRequestDTO;
import com.horapp.presentation.dto.response.UserResponseDTO;

import java.util.List;

public interface UserService {

    UserResponseDTO findById(Long id);

    User findByUsername(String username);

    String save(UserRequestDTO userRequestDTO);

    String deleteById(Long id);
}
