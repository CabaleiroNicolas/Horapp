package com.horapp.service.impl;

import com.horapp.exception.user.UserCreationException;
import com.horapp.persistence.entity.User;
import com.horapp.persistence.repository.UserRepository;
import com.horapp.presentation.dto.request.UserRequestDTO;
import com.horapp.presentation.dto.response.UserResponseDTO;
import com.horapp.service.UserService;
import com.horapp.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserResponseDTO> findAll() {
        return userRepository.findByEnabledTrue().stream()
                .map(this::buildUserResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with Id = " + id));
        return buildUserResponseDTO(user);
    }


    @Override
    public User findEntityById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()){
            throw new NotFoundException("User not found with Id = " + id);
        }
        return optionalUser.get();
    }
    @Override
    public User findByUsername(String username){
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if(optionalUser.isEmpty()){
            throw new NotFoundException("User not found with username = ".concat(username));
        }
        return optionalUser.get();
    }

    @Override
    public String save(UserRequestDTO userRequestDTO) {
            Optional<User> optionalUser = userRepository.findByUsername(userRequestDTO.username());
            if(optionalUser.isPresent()){
                throw new UserCreationException("The user with username " + userRequestDTO.username() + " is already created");
            }
            User user = new User();
            user.setUsername(userRequestDTO.username());
            user.setName(userRequestDTO.name());
            user.setEmail(userRequestDTO.email());
            user.setLastname(userRequestDTO.lastname());
            user.setPassword(passwordEncoder.encode(userRequestDTO.password()));
            user.setRole(Role.STUDENT);
            user.setEnabled(true);
            user.setAccountNonLocked(true);
            userRepository.save(user);
            return "The user was created successfully";
    }

    @Override
    public String deleteById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()){
            throw new NotFoundException("User not found with Id = " + id);
        }
        User userToDelete = optionalUser.get();
        userToDelete.setEnabled(false);
        userRepository.save(userToDelete);
        return "The User with ID " + userToDelete.getIdUser()+ " was deleted successfully";
    }

    private UserResponseDTO buildUserResponseDTO(User user) {
        return new UserResponseDTO(
                user.getIdUser(),
                user.getUsername(),
                user.getName(),
                user.getLastname(),
                user.getEmail());
    }
}
