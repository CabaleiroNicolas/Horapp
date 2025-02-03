package com.horapp.service;

import com.horapp.exception.user.UserCreationException;
import com.horapp.persistence.entity.User;
import com.horapp.persistence.repository.UserRepository;
import com.horapp.presentation.dto.request.UserRequestDTO;
import com.horapp.presentation.dto.response.UserResponseDTO;
import com.horapp.util.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.webjars.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Sql(scripts = {"/db_templates_test/UserUseCaseTestInserts.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Sql(scripts = {"/db_templates_test/DeleteAllTemplateSqlTest.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    @Test
    public void saveTest_successful(){
        String  expectedName = "Ivan",
                expectedLastname = "Ochoa",
                expectedUsername = "ivaan8a98",
                expectedEmail = "ivan@mail.com",
                expectedResponse = "The user was created successfully";
        Role expectedRole = Role.STUDENT;
        UserRequestDTO request = new UserRequestDTO(expectedUsername, expectedName, expectedLastname, expectedEmail, "Aa123456#");
        String response = userService.save(request);
        User savedUser = userRepository.findByUsername(expectedUsername).get();
        assertEquals(expectedResponse, response);
        assertEquals(expectedUsername, savedUser.getUsername());
        assertEquals(expectedName, savedUser.getName());
        assertEquals(expectedEmail, savedUser.getEmail());
        assertEquals(expectedLastname, savedUser.getLastname());
        assertEquals(expectedRole, savedUser.getRole());
    }

    @Test
    public void saveTest_WhenUsernameAlreadyExist(){
        String username = "NicoCab123";
        UserRequestDTO request = new UserRequestDTO(username, "Nicolas", "Cabaleiro", "nicolas@mail.com", "Aa123456#");
        assertThrows(UserCreationException.class, () -> userService.save(request));
    }

    @Test
    public void saveTest_WhenEmailAlreadyExist(){
        String email = "nico@mail.com";
        UserRequestDTO request = new UserRequestDTO("NicoCAB123", "Nicolas", "Cabaleiro", email, "Aa123456#");
        assertThrows(DataIntegrityViolationException.class, () -> userService.save(request));
    }

    @Test
    public void findByIdTest_successful(){
        Long idStudent = 500001L;
        String  expectedName = "Matias",
                expectedLastname = "Alderete",
                expectedUsername = "Matii4k",
                expectedEmail = "matias@mail.com";
        UserResponseDTO response = userService.findById(idStudent);
        assertEquals(idStudent, response.id());
        assertEquals(expectedName, response.name());
        assertEquals(expectedEmail, response.email());
        assertEquals(expectedLastname, response.lastname());
        assertEquals(expectedUsername, response.username());
    }

    @Test
    public void findByIdTest_WhenUserDoesNotExist(){
        Long idStudent = 1L;
        assertThrows(NotFoundException.class, () -> userService.findById(idStudent));
    }

    @Test
    public void deleteByIdTest_successful(){
        Long idStudent = 500002L;
        String expectedResponse = "The User with ID " + idStudent+ " was deleted successfully" ;
        String response = userService.deleteById(idStudent);
        assertEquals(expectedResponse, response);
    }

    @Test
    public void deleteByIdTest_WhenUserDoesNotExist(){
        Long idStudent = 1L;
        assertThrows(NotFoundException.class, () -> userService.deleteById(idStudent));
    }

}
