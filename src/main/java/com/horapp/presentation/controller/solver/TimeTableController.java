package com.horapp.presentation.controller.solver;
import com.horapp.domain.CourseOptaPlanner;
import com.horapp.domain.TimeTableOptaPlanner;
import com.horapp.presentation.dto.ScheduleAssignedDTO;
import com.horapp.presentation.mapper.ScheduleAssignedDTOMapper;
import com.horapp.solver.TimeTableConstraintProvider;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.config.solver.SolverConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.List;

@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/timetable")
public class TimeTableController {


    SolverConfig solverConfig = new SolverConfig()
            .withSolutionClass(TimeTableOptaPlanner.class) // Clase solución
            .withEntityClasses(CourseOptaPlanner.class) // Clases de entidades planificables
            .withConstraintProviderClass(TimeTableConstraintProvider.class)
            .withTerminationSpentLimit(Duration.ofSeconds(5));

    @PostMapping("/solve")
    public TimeTableOptaPlanner solve(@RequestBody TimeTableOptaPlanner problem){

        System.out.println(problem.getCourses());

        SolverFactory<TimeTableOptaPlanner> solverFactory = SolverFactory.create(solverConfig);
        Solver<TimeTableOptaPlanner> solver = solverFactory.buildSolver();

        TimeTableOptaPlanner solvedTimeTable = solver.solve(problem);

        return solvedTimeTable;

    }

    @PostMapping("/solver")
    public ResponseEntity<List<ScheduleAssignedDTO>> solver(@RequestBody TimeTableOptaPlanner problem) {

        SolverFactory<TimeTableOptaPlanner> solverFactory = SolverFactory.create(solverConfig);
        Solver<TimeTableOptaPlanner> solver = solverFactory.buildSolver();
        problem.sortSchedules();
        TimeTableOptaPlanner solvedTimeTable = solver.solve(problem);

        return ResponseEntity.ok(ScheduleAssignedDTOMapper.toScheduleAssignedDTOList(solvedTimeTable));
    }





}
