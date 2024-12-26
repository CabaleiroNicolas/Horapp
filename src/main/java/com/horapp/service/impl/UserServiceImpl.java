package com.horapp.service.impl;

import com.horapp.exception.user.UserCreationException;
import com.horapp.exception.user.UserNotFoundException;
import com.horapp.persistence.entity.User;
import com.horapp.persistence.repository.UserRepository;
import com.horapp.presentation.dto.request.UserRequestDTO;
import com.horapp.presentation.dto.response.UserResponseDTO;
import com.horapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserResponseDTO> findAll() {
        ModelMapper modelMapper = new ModelMapper();
        return userRepository.findByDeletedFalse().stream()
                .map(user -> modelMapper.map(user, UserResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(!optionalUser.isPresent()){
            throw new UserNotFoundException(id);
        }
        ModelMapper modelMapper = new ModelMapper();
        User user = optionalUser.get();
        return modelMapper.map(user, UserResponseDTO.class);
    }

    @Override
    public User findEntityById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(!optionalUser.isPresent()){
            throw new UserNotFoundException(id);
        }
        return optionalUser.get();
    }
    @Override
    public User findByUsername(String username){
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if(!optionalUser.isPresent()){
            throw new UserNotFoundException(username);
        }
        User user = optionalUser.get();
        return user;
    }

    @Override
    public UserResponseDTO save(UserRequestDTO userRequestDTO) {
        if(userRequestDTO.getUsername().isEmpty()
                || userRequestDTO.getName().isEmpty()
                || userRequestDTO.getLastname().isEmpty()
                || userRequestDTO.getEmail().isEmpty()){
            throw new NullPointerException("The fields username, name, lastname and email must not be empty");
        }
       try{
           Optional<User> optionalUser = userRepository.findByUsername(userRequestDTO.getUsername());
           if(optionalUser.isPresent()){
               throw new UserCreationException("The user with username " + userRequestDTO.getUsername() + " is already created");
           }
               ModelMapper modelMapper = new ModelMapper();
               User user = new User();
               user.setUsername(userRequestDTO.getUsername());
               user.setName(userRequestDTO.getName());
               user.setEmail(userRequestDTO.getEmail());
               user.setLastname(userRequestDTO.getLastname());
               userRepository.save(user);
               return modelMapper.map(user, UserResponseDTO.class);
       } catch (UserCreationException e) {
           throw new UserCreationException(e.getMessage(), e);
       } catch (DataIntegrityViolationException e) {
           throw new UserCreationException("Data integrity violation while creating the user: " + e.getMessage(), e);
       }catch (Exception e){
           throw new UserCreationException("An unexpected error occurred while creating the user.", e);
       }
    }

    @Override
    public String deleteById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(!optionalUser.isPresent()){
            throw new UserNotFoundException(id);
        }
        User userToDelete = optionalUser.get();
        userToDelete.setDeleted(true);
        userRepository.save(userToDelete);
        return "The User with ID " + userToDelete.getIdUser()+ " was deleted successfully";

    }
}
