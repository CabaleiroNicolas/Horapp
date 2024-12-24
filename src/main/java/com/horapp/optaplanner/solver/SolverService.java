package com.horapp.optaplanner.solver;

import com.horapp.presentation.dto.request.TimeTableOptaRequestDTO;
import com.horapp.presentation.dto.response.TimeTableOptaResponseDTO;


public interface SolverService {
    TimeTableOptaResponseDTO solveProblem(TimeTableOptaRequestDTO timeTableOptaRequestDTO);
}
