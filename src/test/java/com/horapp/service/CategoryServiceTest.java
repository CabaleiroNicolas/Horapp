package com.horapp.service;

import com.horapp.persistence.repository.CategoryRepository;
import com.horapp.presentation.dto.response.CategoryResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Sql(scripts = {"/db_templates_test/CategoryUseCaseTestInserts.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Sql(scripts = {"/db_templates_test/DeleteAllTemplateSqlTest.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
public class CategoryServiceTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @Test
    public void findAllTest_successful(){
        List<Long> expectedIds = Arrays.asList(300000L, 300001L, 300002L);
        List<String> expectedNames = Arrays.asList("Hora incorrecta", "Dia incorrecto", "Otro");
        List<String> expectedDescriptions = Arrays
                .asList("La hora cargada en el course no corresponse con la real o esta desactualizada",
                        "El dia cargado en el course no corresponse con el real o esta desactualizado",
                        "La causa del problema no se encuentra explicitada entre las opciones disponibles");
        List<CategoryResponseDTO> response = categoryService.findAll();
        assertEquals(expectedIds.size(), response.size());
        assertEquals(expectedNames.size(), response.size());
        assertEquals(expectedDescriptions.size(), response.size());
        for (int i = 0; i < response.size(); i++) {
            assertEquals(expectedIds.get(i), response.get(i).idCategory());
            assertEquals(expectedNames.get(i), response.get(i).categoryName());
            assertEquals(expectedDescriptions.get(i), response.get(i).descriptionName());
        }

    }
}
