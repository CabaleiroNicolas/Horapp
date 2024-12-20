package com.horapp.optaplanner.solver;

import com.horapp.dto.response.ScheduleAssignedDTO;
import com.horapp.optaplanner.modeldomainOP.TimeTableOptaPlanner;
import com.horapp.optaplanner.presentation.mapper.ScheduleAssignedDTOMapper;

import java.util.List;

public interface SolverService {
    List<ScheduleAssignedDTO> solveProblem(TimeTableOptaPlanner problem);
}
