package com.horapp.optaplanner.solver;

import com.horapp.optaplanner.modeldomainOP.CourseOptaPlanner;
import com.horapp.optaplanner.modeldomainOP.DayAndTimeOptaPlanner;
import com.horapp.optaplanner.modeldomainOP.ScheduleOptaPlanner;
import com.horapp.optaplanner.modeldomainOP.TimeTableOptaPlanner;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.*;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;


public class TimeTableConstraintProvider implements ConstraintProvider {

    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
       Constraint[] constraint = new Constraint[]{
               noOverlapConstraint(constraintFactory),
               minimizeTotalIdleTime(constraintFactory),
               minimCoursePerDays(constraintFactory)
               //groupSchedulesOnSameDay(constraintFactory)
       };
        return constraint;
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
                // Unir cada curso con todos los DayAndTimeOptaPlanner (problem facts)
                .join(DayAndTimeOptaPlanner.class)
                // Filtrar solo los DayAndTime del horario asignado del curso
                .filter((course, dayAndTime) ->
                        course.getAssignedSchedule().getDayAndTimes().contains(dayAndTime))
                // Agrupar por día y contar cursos únicos
                .groupBy(
                        (course, dayAndTime) -> dayAndTime.getDay(),
                        ConstraintCollectors.countDistinct((course, dayAndTime) -> course)
                )
                .join(TimeTableOptaPlanner.class)
                .filter((day, courseCount, timetable) -> courseCount < timetable.getMinimumCoursePerDay())
                .penalize(HardSoftScore.ONE_SOFT
                )
                .asConstraint("Minimize days with courses");
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
                .join(TimeTableOptaPlanner.class)
                // Filtrar bloques que empiezan antes de la hora mínima
                .filter((course, dayAndTime, timeTable) ->
                        dayAndTime.getStartTime().isBefore(timeTable.getEarliestStartTime()))
                // Penalizar por la suma de minutos de inicio anticipado
                .penalize(HardSoftScore.ONE_SOFT,
                        (course, dayAndTime, timeTable) -> calculateEarlyMinutes(dayAndTime, timeTable))
                .asConstraint("No early classes");
    }

    // Método auxiliar para calcular minutos antes de earliestStartTime
    private int calculateEarlyMinutes(DayAndTimeOptaPlanner dayAndTime, TimeTableOptaPlanner timeTable) {
        LocalTime earliestStart = timeTable.getEarliestStartTime();
        return (int) Duration.between(dayAndTime.getStartTime(), earliestStart).toMinutes();
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
                .join(TimeTableOptaPlanner.class)
                // Filtrar bloques que terminan después de la hora máxima
                .filter((course, dayAndTime, timeTable) ->
                        dayAndTime.getEndTime().isAfter(timeTable.getLatestEndTime()))
                // Penalizar por minutos excedidos
                .penalize(HardSoftScore.ONE_SOFT,
                        (course, dayAndTime, timeTable) -> calculateLateMinutes(dayAndTime, timeTable))
                .asConstraint("No late classes");
    }

    // Método auxiliar para calcular minutos después de latestEndTime
    private int calculateLateMinutes(DayAndTimeOptaPlanner dayAndTime, TimeTableOptaPlanner timeTable) {
        LocalTime latestEnd = timeTable.getLatestEndTime();
        return (int) Duration.between(latestEnd, dayAndTime.getEndTime()).toMinutes();
    }
    //------------------------------------------------------------------------------------------------------------------

}
