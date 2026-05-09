package com.estudiantes.gestion_estudiantes.controller;

import com.estudiantes.gestion_estudiantes.application.dto.StudentRequestDTO;
import com.estudiantes.gestion_estudiantes.application.dto.StudentResponseDTO;
import com.estudiantes.gestion_estudiantes.domain.port.in.StudentInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentInputPort studentInputPort;

    @GetMapping("/get-all")
    public ResponseEntity<List<StudentResponseDTO>> getAllStudents() {
        log.info("GET /api/students/get-all");
        return ResponseEntity.ok(studentInputPort.getAllStudents());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable UUID id) {
        log.info("GET /api/students/get/{}", id);
        return ResponseEntity.ok(studentInputPort.getStudentById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<StudentResponseDTO> registerStudent(@Valid @RequestBody StudentRequestDTO request) {
        log.info("POST /api/students/register - {}", request.getName());
        StudentResponseDTO response = studentInputPort.createStudent(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(
            @PathVariable UUID id, @Valid @RequestBody StudentRequestDTO request) {
        log.info("PUT /api/students/update/{}", id);
        return ResponseEntity.ok(studentInputPort.updateStudent(id, request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable UUID id) {
        log.info("DELETE /api/students/delete/{}", id);
        studentInputPort.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}