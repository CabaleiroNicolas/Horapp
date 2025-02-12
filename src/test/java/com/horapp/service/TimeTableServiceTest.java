package com.horapp.service;

import com.horapp.persistence.entity.TimeTable;
import com.horapp.persistence.repository.TimeTableRepository;
import com.horapp.presentation.dto.response.TimeTableResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.webjars.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Sql(scripts = {"/db_templates_test/TimeTableUseCaseTestInserts.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Sql(scripts = {"/db_templates_test/DeleteAllTemplateSqlTest.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
public class TimeTableServiceTest {

    @Autowired
    private TimeTableService timeTableService;

    @Autowired
    private TimeTableRepository timeTableRepository;

    @Test
    public void findByIdTest_successful(){
        Long idTimeTable = 100000L;
        String expectedUsername = "MatiiasAld", expectedCourseName = "ALGEBRA";
        TimeTableResponseDTO response = timeTableService.findById(idTimeTable);
        assertEquals(response.id(), idTimeTable);
        assertEquals(response.username(), expectedUsername);
        assertEquals(response.courses().get(0), expectedCourseName);
    }

    @Test
    public void findByIdTest_WhenTimeTableDoesNotExist(){
        Long idTimeTable = 1L;
        assertThrows(NotFoundException.class, () -> timeTableService.findById(idTimeTable));
    }

    @Test
    public void deleteByIdTest_successful(){
        Long idTimeTable = 100001L;
        String expectedResponse = "The TimeTable with id " + idTimeTable + " has been deleted successfully";
        String response = timeTableService.deleteById(idTimeTable);
        assertEquals(response, expectedResponse);
        TimeTable timeTableDeleted = timeTableRepository.findById(idTimeTable).get();
        assertTrue(timeTableDeleted.isDeleted());
    }

    @Test
    public void deleteByIdTest_WhenTimeTableDoesNotExist(){
        Long idTimeTable = 1L;
        assertThrows(NotFoundException.class, () -> timeTableService.findById(idTimeTable));
    }

}
