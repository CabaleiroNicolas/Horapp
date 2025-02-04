package com.horapp.service;

import com.horapp.presentation.dto.request.CourseRequestDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Sql(scripts = {"/db_templates_test/CourseUseCaseTestInserts.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Sql(scripts = {"/db_templates_test/DeleteAllTemplateSqlTest.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
public class CourseServiceTest {

    @Autowired
    private CourseService courseService;

    @Test
    void saveTest_Success() {

        Long majorId = 100000L;
        Long userId = 100000L;

        CourseRequestDTO courseRequest = new CourseRequestDTO("Matematica Discreta", majorId, null, null);
        assertDoesNotThrow(() -> courseService.save(courseRequest));

        CourseRequestDTO courseRequestWithUser = new CourseRequestDTO("Matematica Discreta", majorId, userId, null);
        assertDoesNotThrow(() -> courseService.save(courseRequestWithUser));

    }
}
