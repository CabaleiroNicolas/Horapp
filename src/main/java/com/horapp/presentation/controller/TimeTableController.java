package com.horapp.presentation.controller;

import com.horapp.presentation.dto.request.TimeTableOptaRequestDTO;
import com.horapp.presentation.dto.response.TimeTableOptaResponseDTO;
import com.horapp.optaplanner.solver.SolverService;
import com.horapp.presentation.dto.response.TimeTableResponseDTO;
import com.horapp.service.TimeTableService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/timetables")
public class TimeTableController {

    private final TimeTableService timeTableService;
    private final SolverService solverService;

    public TimeTableController(TimeTableService timeTableService, SolverService solverService) {
        this.timeTableService = timeTableService;
        this.solverService = solverService;
    }


    @Operation(
            summary = "..",
            description = "..",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Request body con la lista de cursos a ordenar con sus respectivas comisiones",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TimeTableOptaRequestDTO.class)
                    )
            ))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TimeTableOptaResponseDTO.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)})

    @PostMapping("/solve")
    public ResponseEntity<TimeTableOptaResponseDTO> solver(@RequestBody TimeTableOptaRequestDTO timeTableOptaRequestDTO) {

        TimeTableOptaResponseDTO solvedTimeTable = solverService.solveProblem(timeTableOptaRequestDTO);

        return ResponseEntity.ok(solvedTimeTable);
    }

    @Operation(
            summary = "Obtener una TimeTable por su ID",
            description = "Devuelve una timetable"
            )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TimeTableOptaResponseDTO.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)})
    @GetMapping("/{timetableId}")
    public ResponseEntity<TimeTableResponseDTO> findById(@PathVariable Long timetableId){
        return new ResponseEntity<>(timeTableService.findById(timetableId), HttpStatus.OK);
    }


    @Operation(
            summary = "Deshabilitar una TimeTable",
            description = "Deshabilita una timetable",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Path variable con el id de la tabla que se desea deshabilitar",
                    required = true
            ))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TimeTableOptaResponseDTO.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)})
    @DeleteMapping("/{timetableId}")
    public ResponseEntity<String> deleteById(@PathVariable Long timetableId){
        return new ResponseEntity<>(timeTableService.deleteById(timetableId), HttpStatus.OK);
    }
}
