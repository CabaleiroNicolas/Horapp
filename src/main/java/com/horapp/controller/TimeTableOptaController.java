package com.horapp.controller;

import com.horapp.dto.request.TimeTableOptaRequestDTO;
import com.horapp.dto.response.TimeTableOptaResponseDTO;
import com.horapp.optaplanner.solver.SolverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/timetable")
public class TimeTableOptaController {

    @Autowired
    private SolverService solverService;

    @PostMapping("/solve")
    public ResponseEntity<TimeTableOptaResponseDTO> solver(@RequestBody TimeTableOptaRequestDTO timeTableOptaRequestDTO) {

        TimeTableOptaResponseDTO solvedTimeTable = solverService.solveProblem(timeTableOptaRequestDTO);

        return ResponseEntity.ok(solvedTimeTable);
    }


}
