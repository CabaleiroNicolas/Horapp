package com.horapp.controller;

import com.horapp.dto.response.ScheduleAssignedDTO;
import com.horapp.optaplanner.modeldomainOP.TimeTableOptaPlanner;
import com.horapp.optaplanner.solver.SolverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/timetable")
public class TimeTableOptaController {

    private final SolverService solverService;

    @PostMapping("/solve")
    public ResponseEntity<List<ScheduleAssignedDTO>> solver(@RequestBody TimeTableOptaPlanner problem) {

        List<ScheduleAssignedDTO> solvedTimeTable = solverService.solveProblem(problem);

        return ResponseEntity.ok(solvedTimeTable);
    }


}
