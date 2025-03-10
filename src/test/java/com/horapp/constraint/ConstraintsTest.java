package com.horapp.constraint;

import com.horapp.optaplanner.modeldomainOP.CourseOptaPlanner;
import com.horapp.optaplanner.modeldomainOP.DayAndTimeOptaPlanner;
import com.horapp.optaplanner.modeldomainOP.ScheduleOptaPlanner;
import com.horapp.optaplanner.modeldomainOP.TimeTableOptaPlanner;
import com.horapp.optaplanner.solver.TimeTableConstraintProvider;
import org.junit.jupiter.api.Test;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.config.solver.SolverConfig;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ConstraintsTest {

    SolverConfig solverConfig = new SolverConfig()
            .withSolutionClass(TimeTableOptaPlanner.class) // Clase solución
            .withEntityClasses(CourseOptaPlanner.class) // Clases de entidades planificables
            .withConstraintProviderClass(TimeTableConstraintProvider.class)
            .withTerminationSpentLimit(Duration.ofSeconds(5));
    SolverFactory<TimeTableOptaPlanner> solverFactory = SolverFactory.create(solverConfig);
    Solver<TimeTableOptaPlanner> solver = solverFactory.buildSolver();

    @Test
    void testMinimizeTotalIdleTime() {
        // Curso 1: Lunes 10:00-11:00 y 12:00-13:00
        ScheduleOptaPlanner schedule1 = new ScheduleOptaPlanner(List.of(
                new DayAndTimeOptaPlanner(DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(11, 0)),
                new DayAndTimeOptaPlanner(DayOfWeek.MONDAY, LocalTime.of(12, 0), LocalTime.of(13, 0))
        ));

        // Curso 2: Lunes 14:00-15:00
        ScheduleOptaPlanner schedule2 = new ScheduleOptaPlanner(List.of(
                new DayAndTimeOptaPlanner(DayOfWeek.MONDAY, LocalTime.of(14, 0), LocalTime.of(15, 0))
        ));

        CourseOptaPlanner course1 = new CourseOptaPlanner(schedule1);
        CourseOptaPlanner course2 = new CourseOptaPlanner(schedule2);

        TimeTableOptaPlanner timetable = new TimeTableOptaPlanner(List.of(course1, course2));
        timetable = solver.solve(timetable);

        // Tiempo muerto total en Lunes: 60 (11-12) + 60 (13-14) = 120 minutos
        assertThat(timetable.getScore()).isEqualTo(-120);
    }
}
