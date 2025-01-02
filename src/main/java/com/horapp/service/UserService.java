package com.horapp.service;

import com.horapp.persistence.entity.User;
import com.horapp.presentation.dto.request.UserRequestDTO;
import com.horapp.presentation.dto.response.UserResponseDTO;

import java.util.List;

public interface UserService {
    List<UserResponseDTO> findAll();

    UserResponseDTO findById(Long id);

    User findEntityById(Long id);

    User findByUsername(String username);

    String save(UserRequestDTO userRequestDTO);

    String deleteById(Long id);
}
