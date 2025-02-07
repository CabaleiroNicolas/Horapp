package com.horapp.service;


import com.horapp.presentation.dto.auth.AuthenticationRequest;
import com.horapp.service.auth.AuthenticationService;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Sql(scripts = {"/db_templates_test/AuthenticationUseCaseTestInserts.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Sql(scripts = {"/db_templates_test/DeleteAllTemplateSqlTest.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
public class AuthenticationServiceTest {

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void loginTest_Successful() {

        String username = "nicoTest";
        String password = "test";

        assertDoesNotThrow(()-> authenticationService.login(new AuthenticationRequest(username, password)));

    }

    @Test
    public void loginTest_Rejected() {

        String username = "nicoTest";
        String badPassword = "test1";

        assertThrows(BadCredentialsException.class, ()-> authenticationService.login(new AuthenticationRequest(username, badPassword)),
                "invalid username or password");

    }

}
