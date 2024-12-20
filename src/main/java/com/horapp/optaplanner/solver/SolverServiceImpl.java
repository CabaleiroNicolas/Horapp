package com.horapp.optaplanner.solver;

import com.horapp.dto.response.ScheduleAssignedDTO;
import com.horapp.optaplanner.modeldomainOP.CourseOptaPlanner;
import com.horapp.optaplanner.modeldomainOP.TimeTableOptaPlanner;
import com.horapp.optaplanner.presentation.mapper.ScheduleAssignedDTOMapper;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.config.solver.SolverConfig;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class SolverServiceImpl implements SolverService{

    SolverConfig solverConfig = new SolverConfig()
            .withSolutionClass(TimeTableOptaPlanner.class) // Clase solución
            .withEntityClasses(CourseOptaPlanner.class) // Clases de entidades planificables
            .withConstraintProviderClass(TimeTableConstraintProvider.class)
            .withTerminationSpentLimit(Duration.ofSeconds(5));



    public List<ScheduleAssignedDTO> solveProblem(TimeTableOptaPlanner problem){

        SolverFactory<TimeTableOptaPlanner> solverFactory = SolverFactory.create(solverConfig);
        Solver<TimeTableOptaPlanner> solver = solverFactory.buildSolver();
        problem.sortSchedules();
        TimeTableOptaPlanner solvedTimeTable = solver.solve(problem);

        List<ScheduleAssignedDTO> result = ScheduleAssignedDTOMapper.toScheduleAssignedDTOList(solvedTimeTable);
        return result;
    }
}
