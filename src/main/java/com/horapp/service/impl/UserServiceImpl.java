package com.horapp.service.impl;

import com.horapp.persistence.entity.User;
import com.horapp.persistence.repository.UserRepository;
import com.horapp.presentation.dto.request.UserRequestDTO;
import com.horapp.presentation.dto.response.UserResponseDTO;
import com.horapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
        try{
            Optional<User> optionalUser = userRepository.findById(id);
            if(optionalUser.isPresent()){
                ModelMapper modelMapper = new ModelMapper();
                User user = optionalUser.get();
                return modelMapper.map(user, UserResponseDTO.class);
            }
        } catch (Exception e) {
            System.err.println("User with ID: " + e.getMessage() + "don´t exists in database");
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public User findEntityById(Long id) {
        try{
            Optional<User> optionalUser = userRepository.findById(id);
            if(optionalUser.isPresent()){
                User user = optionalUser.get();
                return user;
            }
        } catch (Exception e) {
            System.err.println("User with ID: " + e.getMessage() + "don´t exists in database");
            throw new RuntimeException(e);
        }
        return null;
    }
    @Override
    public User findByUsername(String username){
        try{
            Optional<User> optionalUser = userRepository.findByUsername(username);
            if(optionalUser.isPresent()){
                User user = optionalUser.get();
                return user;
            }
        } catch (Exception e) {
            System.err.println("User with username: " + e.getMessage() + "don´t exists in database");
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public UserResponseDTO save(UserRequestDTO userRequestDTO) {
       try{
           Optional<User> optionalUser = userRepository.findByUsername(userRequestDTO.getUsername());
           if(!optionalUser.isPresent()){
               ModelMapper modelMapper = new ModelMapper();
               User user = new User();
               user.setUsername(userRequestDTO.getUsername());
               user.setName(userRequestDTO.getName());
               user.setEmail(userRequestDTO.getEmail());
               user.setLastname(userRequestDTO.getLastname());
               userRepository.save(user);
               return modelMapper.map(user, UserResponseDTO.class);
           }
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
       return new UserResponseDTO();
    }

    @Override
    public String deleteById(Long id) {
        try{
            Optional<User> optionalUser = userRepository.findById(id);
            if(optionalUser.isPresent()){
                User userToDelete = optionalUser.get();
                userToDelete.setDeleted(true);
                userRepository.save(userToDelete);
                return "The User with ID " + userToDelete.getIdUser()+ " was deleted successfully";
            }
        } catch (Exception e) {
            System.err.println("User with ID: " + e.getMessage() + "don´t exists in database");
            throw new RuntimeException(e);
        }
        return "";
    }
}
