package com.horapp.service;

import com.horapp.persistence.entity.Major;
import com.horapp.persistence.repository.MajorRepository;
import com.horapp.presentation.dto.request.MajorRequestDTO;
import com.horapp.presentation.dto.response.MajorResponseDTO;
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
@Sql(scripts = {"/db_templates_test/MajorUseCaseTestInserts.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Sql(scripts = {"/db_templates_test/DeleteAllTemplateSqlTest.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
public class MajorServiceTest {


    @Autowired
    private MajorService majorService;

    @Autowired
    private MajorRepository majorRepository;

    @Test
    public void saveTest_successful(){
        String majorName = "Ingenieria Electronica";
        Long idUser = 100000L;
        MajorRequestDTO request = new MajorRequestDTO(majorName, idUser);
        MajorResponseDTO response = majorService.saveMajor(request);
        Major majorSaved = majorRepository.findByDeletedFalseAndUser_IdUser(idUser).get(0);
        assertEquals(majorName, majorSaved.getMajorName());
    }

    @Test
    public void saveTest_WhenUserDoesNotExist(){
        String majorName = "Ingenieria Electronica";
        Long idUser = 1L;
        MajorRequestDTO request = new MajorRequestDTO(majorName, idUser);
        assertThrows(DataIntegrityViolationException.class, () -> majorService.saveMajor(request));
    }

    @Test
    public void findByIdTest_successful(){
        Long idMajor = 100001L;
        String expectedMajorName = "ISI";
        MajorResponseDTO response = majorService.findById(idMajor);
        assertEquals(expectedMajorName, response.majorName());
    }

    @Test
    public void findByIdTest_WhenMajorDoesNotExist(){
        Long idMajor = 1L;
        assertThrows(NotFoundException.class, ()-> majorService.findById(idMajor));
    }

    @Test
    public void deleteByIdTest_successful(){
        Long idMajor = 100002L;
        String expectedResponse = "The Major with ID " + idMajor + " was deleted successfully";
        String response = majorService.deleteById(idMajor);
        assertEquals(expectedResponse, response);
        Major majorDeleted = majorRepository.findById(idMajor).get();
        assertTrue(majorDeleted.isDeleted());
    }

    @Test
    public void deleteByIdTest_WhenMajorDoesNotExist(){
        Long idMajor = 1L;
        assertThrows(NotFoundException.class, ()-> majorService.deleteById(idMajor));
    }

    @Test
    public void findAllByUserTest_successful(){
        Long idUser = 100003L, expectedIdMajor = 100003L;
        String expectedName = "ELECTRICA";
        MajorResponseDTO majorsByUser = majorService.findAllByUser(idUser).get(0);
        assertEquals(expectedName, majorsByUser.majorName());
        assertEquals(expectedIdMajor, majorsByUser.idMajor());
    }

    @Test
    public void findAllByUserTest_WhenUserDoesNotExist(){
        Long idUser = 1L;
        assertThrows(NotFoundException.class, ()-> majorService.deleteById(idUser));
    }
}
