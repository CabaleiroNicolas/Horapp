package com.horapp.optaplanner.solver;

import com.horapp.optaplanner.modeldomainOP.CourseOptaPlanner;
import com.horapp.optaplanner.modeldomainOP.DayAndTimeOptaPlanner;
import com.horapp.optaplanner.modeldomainOP.ScheduleOptaPlanner;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.*;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;


public class TimeTableConstraintProvider implements ConstraintProvider {

    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[]{
               noOverlapConstraint(constraintFactory),
               minimizeTotalIdleTime(constraintFactory)
               //minimizeDistanceBetweenCourses(constraintFactory),
               //groupSchedulesOnSameDay(constraintFactory)
       };
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


}
