package com.horapp.service.impl;

import com.horapp.exception.user.UserCreationException;
import com.horapp.persistence.entity.User;
import com.horapp.persistence.repository.UserRepository;
import com.horapp.presentation.dto.request.UserRequestDTO;
import com.horapp.presentation.dto.response.UserResponseDTO;
import com.horapp.service.UserService;
import com.horapp.util.Role;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponseDTO findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with Id = " + id));
        return buildUserResponseDTO(user);
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
            User user = new User(
                    true,
                    userRequestDTO.email() ,
                    true,
                    userRequestDTO.lastname(),
                    userRequestDTO.name(),
                    passwordEncoder.encode(userRequestDTO.password()),
                    Role.STUDENT,
                    userRequestDTO.username());
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.findByUsername(username);
    }
}
