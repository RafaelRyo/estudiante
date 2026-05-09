package com.estudiantes.gestion_estudiantes.application.usecase;

import com.estudiantes.gestion_estudiantes.application.dto.StudentRequestDTO;
import com.estudiantes.gestion_estudiantes.application.dto.StudentResponseDTO;
import com.estudiantes.gestion_estudiantes.application.mapper.StudentDtoMapper;
import com.estudiantes.gestion_estudiantes.domain.model.Student;
import com.estudiantes.gestion_estudiantes.domain.port.in.StudentInputPort;
import com.estudiantes.gestion_estudiantes.domain.port.out.StudentOutputPort;
import com.estudiantes.gestion_estudiantes.exception.StudentNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentUseCase implements StudentInputPort {

    private final StudentOutputPort outputPort;
    private final StudentDtoMapper mapper;

    @Override
    public List<StudentResponseDTO> getAllStudents() {
        log.info("Obteniendo todos los estudiantes");
        return outputPort.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public StudentResponseDTO getStudentById(UUID id) {
        log.info("Buscando estudiante con id: {}", id);
        Student student = outputPort.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        return mapper.toResponse(student);
    }

    @Override
    public StudentResponseDTO createStudent(StudentRequestDTO request) {
        log.info("Creando nuevo estudiante: {}", request.getName());
        Student student = mapper.toDomain(request);
        Student saved = outputPort.save(student);
        return mapper.toResponse(saved);
    }

    @Override
    public StudentResponseDTO updateStudent(UUID id, StudentRequestDTO request) {
        log.info("Actualizando estudiante con id: {}", id);
        Student existing = outputPort.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        existing.setName(request.getName());
        existing.setLastNames(request.getLastNames());
        existing.setDateBirth(request.getDateBirth());
        existing.setGrade(request.getGrade());
        Student updated = outputPort.save(existing);
        return mapper.toResponse(updated);
    }

    @Override
    public void deleteStudent(UUID id) {
        log.info("Eliminando estudiante con id: {}", id);
        if (!outputPort.existsById(id)) {
            throw new StudentNotFoundException(id);
        }
        outputPort.deleteById(id);
    }
}
