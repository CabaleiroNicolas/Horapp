package com.horapp.service;

import com.horapp.persistence.entity.DayAndTime;
import com.horapp.persistence.repository.DayAndTimeRepository;
import com.horapp.presentation.dto.request.DayAndTimeRequestDTO;

import com.horapp.presentation.dto.response.DayAndTimeResponseDTO;
import com.horapp.service.impl.DayAndTimeServiceImpl;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.time.DayOfWeek;


@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Sql(scripts = {"/db_templates_test/DayAndTimeUseCaseTestInserts.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Sql(scripts = {"/db_templates_test/DeleteAllTemplateSqlTest.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
class DayAndTimeServiceTest {

    @Autowired
    DayAndTimeServiceImpl dayAndTimeService;
    @Autowired
    DayAndTimeRepository dayAndTimeRepository;


    @Test
    void saveTest_Successful() {

        Long idSchedule = 100000L;
        DayOfWeek expectedDay = DayOfWeek.MONDAY;

        DayAndTimeRequestDTO dayAndTimeRequestDTO = new DayAndTimeRequestDTO(idSchedule, "MONDAY", "08:00", "17:00");
        DayAndTimeResponseDTO response = assertDoesNotThrow(()->dayAndTimeService.save(dayAndTimeRequestDTO));
        DayAndTime dayAndTimeSaved =  dayAndTimeRepository.findById(response.idDayAndTime()).get();

        assertEquals(expectedDay, dayAndTimeSaved.getDay());

    }

    @Test
    void saveTest_WhenDoesNotExistSchedule() {

        Long idSchedule = 1L; // Schedule with id 1 does not exist

        DayAndTimeRequestDTO dayAndTimeRequestDTO = new DayAndTimeRequestDTO(idSchedule, "MONDAY", "08:00", "17:00");
        assertThrows(DataIntegrityViolationException.class, ()->dayAndTimeService.save(dayAndTimeRequestDTO));

    }

}
