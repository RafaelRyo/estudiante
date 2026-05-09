package com.estudiantes.gestion_estudiantes.domain.port.in;

import com.estudiantes.gestion_estudiantes.application.dto.StudentRequestDTO;
import com.estudiantes.gestion_estudiantes.application.dto.StudentResponseDTO;

import java.util.List;
import java.util.UUID;

public interface StudentInputPort {
    List<StudentResponseDTO> getAllStudents();
    StudentResponseDTO getStudentById(UUID id);

    StudentResponseDTO createStudent(StudentRequestDTO request);
    StudentResponseDTO updateStudent(UUID id, StudentRequestDTO request);
    void deleteStudent(UUID id);
}
