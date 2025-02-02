package com.horapp.service;

import com.horapp.persistence.entity.Feedback;
import com.horapp.persistence.repository.FeedbackRepository;
import com.horapp.presentation.dto.request.FeedbackRequestDTO;
import com.horapp.presentation.dto.response.FeedbackResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Sql(scripts = {"/db_templates_test/FeedbackUseCaseTestInserts.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Sql(scripts = {"/db_templates_test/DeleteAllTemplateSqlTest.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
public class FeedbackServiceTest {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired FeedbackService feedbackService;

    @Test
    public void saveTest_successful(){
        Long expectedId = 400000L, expectedCategoryId = 300004L, expectedCourseId = 100010L;
        String expectedDescription = "El nombre correcto es ARCOM";
        FeedbackRequestDTO request = new FeedbackRequestDTO(expectedDescription, List.of(expectedCategoryId), expectedCourseId);
        FeedbackResponseDTO response = feedbackService.save(request);
        Feedback savedFeedback = feedbackRepository.findById(expectedId).get();
        assertEquals(expectedId, savedFeedback.getIdFeedback());
        assertEquals(expectedCourseId, savedFeedback.getCourse().getIdCourse());
        assertEquals(expectedDescription, savedFeedback.getDescriptionName());
        assertEquals(expectedCategoryId, savedFeedback.getCategoryList().get(0).getIdCategory());
    }

    @Test
    public void saveTest_WhenDoesNotExistCourse(){
        Long idCourse = 1L;
        FeedbackRequestDTO request = new FeedbackRequestDTO("El nombre correcto es ARCOM", List.of(300004L), idCourse);
        assertThrows(DataIntegrityViolationException.class, () -> feedbackService.save(request));
    }

    @Test
    public void saveTest_WhenDoesNotExistCategory(){
        Long idCategory = 1L;
        FeedbackRequestDTO request = new FeedbackRequestDTO("El nombre correcto es ARCOM", List.of(idCategory), 100010L);
        assertThrows(DataIntegrityViolationException.class, () -> feedbackService.save(request));
    }

}
