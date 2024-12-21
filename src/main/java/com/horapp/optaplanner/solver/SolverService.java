package com.horapp.optaplanner.solver;

import com.horapp.dto.request.TimeTableOptaRequestDTO;
import com.horapp.dto.response.TimeTableOptaResponseDTO;


public interface SolverService {
    TimeTableOptaResponseDTO solveProblem(TimeTableOptaRequestDTO timeTableOptaRequestDTO);
}
