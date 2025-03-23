package com.horapp.optaplanner.solver;

import com.horapp.optaplanner.modeldomainOP.CourseOptaPlanner;
import com.horapp.optaplanner.modeldomainOP.DayAndTimeOptaPlanner;
import com.horapp.optaplanner.modeldomainOP.ScheduleOptaPlanner;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.*;



import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TimeTableConstraintProvider implements ConstraintProvider {

    private final LocalTime earliestTime = LocalTime.of(14,0);
    private final LocalTime latestTime = LocalTime.of(20,0);
    private final Integer minCourses = 2;

    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {

       List<Constraint> constraints = new ArrayList<>();

        constraints.add(noOverlapConstraint(constraintFactory));
        constraints.add(minimizeTotalIdleTime(constraintFactory));
        constraints.add(minimCoursePerDays(constraintFactory));
        constraints.add(enforceEarliestStartTime(constraintFactory));
        constraints.add(enforceLatestEndTime(constraintFactory));
        return constraints.toArray(new Constraint[0]);
    }


    Constraint noOverlapConstraint(ConstraintFactory constraintFactory) {
        return constraintFactory
                // Iterar sobre todos los pares únicos de cursos
                .forEachUniquePair(CourseOptaPlanner.class,
                        // Ambos cursos deben tener un horario asignado
                        Joiners.filtering((c1, c2) ->
                                c1.getAssignedSchedule() != null &&
                                        c2.getAssignedSchedule() != null
                        ),
                        // Al menos un día en común con superposición de horarios
                        Joiners.filtering((c1, c2) ->
                                haveOverlapOnAnyDay(
                                        c1.getAssignedSchedule(),
                                        c2.getAssignedSchedule()
                                )
                        )
                )
                // Penalizar como Hard (una penalización por par conflictivo)
                .penalize(HardSoftScore.ONE_HARD)
                .asConstraint("No overlap");
    }

    // Método auxiliar para detectar superposición en cualquier día
    private boolean haveOverlapOnAnyDay(ScheduleOptaPlanner s1, ScheduleOptaPlanner s2) {
        return s1.getDayAndTimes().stream()
                .anyMatch(dt1 -> s2.getDayAndTimes().stream()
                        .anyMatch(dt2 ->
                                dt1.getDay() == dt2.getDay() && // Mismo día
                                        dt1.getStartTime().isBefore(dt2.getEndTime()) &&
                                        dt1.getEndTime().isAfter(dt2.getStartTime()) // Superposición
                        )
                );
    }

    //MINIMA CANTIDAD DE MATERIAS POR DIA
    Constraint minimCoursePerDays(ConstraintFactory constraintFactory) {
        return constraintFactory.forEach(CourseOptaPlanner.class)
                .filter(course -> course.getAssignedSchedule() != null)
                // Unir con los DayAndTimeOptaPlanner (problem facts)
                .join(DayAndTimeOptaPlanner.class)
                .filter((course, dayAndTime) ->
                        course.getAssignedSchedule().getDayAndTimes().contains(dayAndTime))
                // Agrupar por día y contar cursos únicos
                .groupBy(
                        (course, dayAndTime) -> dayAndTime.getDay(),
                        ConstraintCollectors.countDistinct((course, dayAndTime) -> course)
                )

                // Filtrar días con menos cursos de los requeridos
                .filter((day, courseCount) ->
                        courseCount < /*minimCourse*/minCourses && courseCount > 0)
                // Penalizar por CADA DÍA que incumple, multiplicando por la gravedad
                .penalize(HardSoftScore.ONE_SOFT,
                        (day, courseCount) ->
                                // Penalización por día: (Diferencia * Penalty) o valor fijo
                                (1 - courseCount) * 100 // Ejemplo: 100 por curso faltante
                )
                .asConstraint("Minimize days with courses below minimum");
    }
    //------------------------------------------------------------------------------------------------------------------


    //MINIMO TIEMPO MUERTO
    Constraint minimizeTotalIdleTime(ConstraintFactory constraintFactory) {
        return constraintFactory.forEach(CourseOptaPlanner.class)
                .filter(course -> course.getAssignedSchedule() != null)
                // Unir con DayAndTimeOptaPlanner (problem fact)
                .join(DayAndTimeOptaPlanner.class)
                .filter((course, dayAndTime) ->
                        course.getAssignedSchedule().getDayAndTimes().contains(dayAndTime))
                .groupBy(
                        (course, dayAndTime) -> dayAndTime.getDay(),
                        ConstraintCollectors.toList((course, dayAndTime) -> dayAndTime)
                )
                .penalize(HardSoftScore.ONE_SOFT,
                        (day, dayTimeList) -> calculateTotalIdleTimeForDay(dayTimeList))
                .asConstraint("Minimize total idle time");
    }


    // Calcula el tiempo muerto total entre clases consecutivas en un día
    private int calculateTotalIdleTimeForDay(List<DayAndTimeOptaPlanner> dayTimeList) {
        if (dayTimeList.size() < 2) {
            return 0; // No hay tiempo muerto si hay menos de 2 clases
        }

        // Ordenar los bloques por hora de inicio
        List<DayAndTimeOptaPlanner> sorted = dayTimeList.stream()
                .sorted(Comparator.comparing(DayAndTimeOptaPlanner::getStartTime))
                .toList();

        int totalIdle = 0;
        for (int i = 1; i < sorted.size(); i++) {
            DayAndTimeOptaPlanner prev = sorted.get(i - 1);
            DayAndTimeOptaPlanner curr = sorted.get(i);
            if (prev.getEndTime().isBefore(curr.getStartTime())) {
                totalIdle += (int) Duration.between(prev.getEndTime(), curr.getStartTime()).toMinutes();
            }
        }
        return totalIdle;
    }
    //------------------------------------------------------------------------------------------------------------------

    //NO EMPIECEN CLASES ANTES DE UNA HORA
    Constraint enforceEarliestStartTime(ConstraintFactory constraintFactory) {
        return constraintFactory.forEach(CourseOptaPlanner.class)
                .filter(course -> course.getAssignedSchedule() != null)
                // Unir con los DayAndTimeOptaPlanner (problem facts) del horario asignado
                .join(DayAndTimeOptaPlanner.class,
                        Joiners.equal(course -> course.getAssignedSchedule().getDayAndTimes(),
                                dayAndTime -> List.of(dayAndTime)))
                // Unir con TimeTable para obtener earliestStartTime
                // Filtrar bloques que empiezan antes de la hora mínima
                .filter((course, dayAndTime) ->
                        dayAndTime.getStartTime().isBefore(earliestTime))
                // Penalizar por la suma de minutos de inicio anticipado
                .penalize(HardSoftScore.ONE_SOFT,
                        (course, dayAndTime) -> calculateEarlyMinutes(dayAndTime, earliestTime))
                .asConstraint("No early classes");
    }

    // Método auxiliar para calcular minutos antes de earliestStartTime
    private int calculateEarlyMinutes(DayAndTimeOptaPlanner dayAndTime, LocalTime time) {
        return (int) Duration.between(dayAndTime.getStartTime(), time).toMinutes();
    }
    //------------------------------------------------------------------------------------------------------------------

    //NO TERMINEN CLASES DESPUES DE UNA HORA
    Constraint enforceLatestEndTime(ConstraintFactory constraintFactory) {
        return constraintFactory.forEach(CourseOptaPlanner.class)
                .filter(course -> course.getAssignedSchedule() != null)
                // Unir con los DayAndTimeOptaPlanner (problem facts)
                .join(DayAndTimeOptaPlanner.class,
                        Joiners.equal(course -> course.getAssignedSchedule().getDayAndTimes(),
                                dayAndTime -> List.of(dayAndTime)))
                // Unir con TimeTable para obtener latestEndTime

                // Filtrar bloques que terminan después de la hora máxima
                .filter((course, dayAndTime) ->
                        dayAndTime.getEndTime().isAfter(latestTime))
                // Penalizar por minutos excedidos
                .penalize(HardSoftScore.ONE_SOFT,
                        (course, dayAndTime) -> calculateLateMinutes(dayAndTime, latestTime))
                .asConstraint("No late classes");
    }

    // Método auxiliar para calcular minutos después de latestEndTime
    private int calculateLateMinutes(DayAndTimeOptaPlanner dayAndTime, LocalTime time) {
        return (int) Duration.between(LocalTime.of(20,30), dayAndTime.getEndTime()).toMinutes();
    }
    //------------------------------------------------------------------------------------------------------------------



}
