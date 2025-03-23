package com.horapp.optaplanner.solver;

import com.horapp.exception.time_table.ProblemNotResolvedException;
import com.horapp.optaplanner.OptaPlannerConstraints;
import com.horapp.presentation.dto.request.TimeTableOptaRequestDTO;
import com.horapp.presentation.dto.response.ScheduleAssignedDTO;
import com.horapp.presentation.dto.response.TimeTableOptaResponseDTO;
import com.horapp.optaplanner.modeldomainOP.CourseOptaPlanner;
import com.horapp.optaplanner.modeldomainOP.TimeTableOptaPlanner;
import com.horapp.optaplanner.presentation.mapper.ScheduleAssignedDTOMapper;
import com.horapp.optaplanner.presentation.mapper.TimeTableOptaMapper;
import com.horapp.persistence.entity.Course;
import com.horapp.service.CourseService;
import com.horapp.service.MajorService;
import com.horapp.service.UserService;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.config.solver.SolverConfig;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;


@Service
public class SolverServiceImpl implements SolverService{

    private final CourseService courseService;
    private final UserService userService;
    private final MajorService majorService;


    public SolverServiceImpl(CourseService courseService, UserService userService, MajorService majorService) {
        this.courseService = courseService;
        this.userService = userService;
        this.majorService = majorService;
    }

    SolverConfig solverConfig = new SolverConfig()
            .withSolutionClass(TimeTableOptaPlanner.class) // Clase solución
            .withEntityClasses(CourseOptaPlanner.class) // Clases de entidades planificables
            .withConstraintProviderClass(TimeTableConstraintProvider.class)
            .withTerminationSpentLimit(Duration.ofSeconds(10));


    @Override
    public TimeTableOptaResponseDTO solveProblem(TimeTableOptaRequestDTO timeTableOptaRequestDTO, String earliestTime, String latestTime, Integer minCOurse) throws ProblemNotResolvedException {

        LocalTime earliestStartTime = !earliestTime.isBlank() ? LocalTime.parse(earliestTime) : LocalTime.of(0, 0);
        LocalTime latestEndTime = !latestTime.isBlank() ? LocalTime.parse(latestTime) : LocalTime.of(23,59);
        Integer minimumCoursePerDay = minCOurse != null ? minCOurse : 0;

        SolverFactory<TimeTableOptaPlanner> solverFactory = SolverFactory.create(solverConfig);
        Solver<TimeTableOptaPlanner> solver = solverFactory.buildSolver();
        List<Course> courses = courseService.findAllByIdList(timeTableOptaRequestDTO.coursesId());

        // Mapear a TimeTableOptaPlanner
        TimeTableOptaPlanner problem = TimeTableOptaMapper.mapToTimeTableOptaPlanner(courses);


        TimeTableOptaPlanner solvedTimeTable = solver.solve(problem);

        // Mapear el resultado al DTO de respuesta.
        List<ScheduleAssignedDTO> result = ScheduleAssignedDTOMapper.toScheduleAssignedDTOList(solvedTimeTable);
        TimeTableOptaResponseDTO responseDTO = new TimeTableOptaResponseDTO(result);

        if(solvedTimeTable.getScore().hardScore() > 0){
            throw new ProblemNotResolvedException("Restriccion dura no cumplida");
        }

        return responseDTO;
    }
}
