package com.horapp.optaplanner.solver;

import com.horapp.presentation.dto.request.TimeTableOptaRequestDTO;
import com.horapp.presentation.dto.response.ScheduleAssignedDTO;
import com.horapp.presentation.dto.response.TimeTableOptaResponseDTO;
import com.horapp.optaplanner.modeldomainOP.CourseOptaPlanner;
import com.horapp.optaplanner.modeldomainOP.TimeTableOptaPlanner;
import com.horapp.optaplanner.presentation.mapper.ScheduleAssignedDTOMapper;
import com.horapp.optaplanner.presentation.mapper.TimeTableOptaMapper;
import com.horapp.persistence.entity.Course;
import com.horapp.persistence.entity.Major;
import com.horapp.persistence.entity.User;
import com.horapp.service.CourseService;
import com.horapp.service.MajorService;
import com.horapp.service.UserService;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.config.solver.SolverConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class SolverServiceImpl implements SolverService{

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @Autowired
    private MajorService majorService;

    SolverConfig solverConfig = new SolverConfig()
            .withSolutionClass(TimeTableOptaPlanner.class) // Clase solución
            .withEntityClasses(CourseOptaPlanner.class) // Clases de entidades planificables
            .withConstraintProviderClass(TimeTableConstraintProvider.class)
            .withTerminationSpentLimit(Duration.ofSeconds(5));


    @Override
    public TimeTableOptaResponseDTO solveProblem(TimeTableOptaRequestDTO timeTableOptaRequestDTO) {

        SolverFactory<TimeTableOptaPlanner> solverFactory = SolverFactory.create(solverConfig);
        Solver<TimeTableOptaPlanner> solver = solverFactory.buildSolver();
        List<Course> courses = courseService.findAllByIdList(timeTableOptaRequestDTO.coursesId());

        // Mapear a TimeTableOptaPlanner
        TimeTableOptaPlanner problem = TimeTableOptaMapper.mapToTimeTableOptaPlanner(courses);
        problem.sortSchedules();

        TimeTableOptaPlanner solvedTimeTable = solver.solve(problem);

        // Mapear el resultado al DTO de respuesta.
        List<ScheduleAssignedDTO> result = ScheduleAssignedDTOMapper.toScheduleAssignedDTOList(solvedTimeTable);
        TimeTableOptaResponseDTO responseDTO = new TimeTableOptaResponseDTO(result);


        //Ver si hard-score es mayor a 0 devolver un mensaje de error.
        System.out.println(solvedTimeTable.getScore());
        return responseDTO;
    }
}
