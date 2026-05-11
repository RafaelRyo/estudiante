package com.estudiantes.gestion_estudiantes.application.mapper;

import com.estudiantes.gestion_estudiantes.application.dto.StudentRequestDTO;
import com.estudiantes.gestion_estudiantes.application.dto.StudentResponseDTO;
import com.estudiantes.gestion_estudiantes.domain.model.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentDtoMapper {

    public Student toDomain(StudentRequestDTO dto) {
        return Student.builder()
                .name(dto.getName())
                .lastNames(dto.getLastNames())
                .dateBirth(dto.getDateBirth())
                .grade(dto.getGrade())
                .build();
    }

    public StudentResponseDTO toResponse(Student domain) {
        return StudentResponseDTO.builder()
                .eid(domain.getEid())
                .name(domain.getName())
                .lastNames(domain.getLastNames())
                .dateBirth(domain.getDateBirth())
                .grade(domain.getGrade())
                .build();
    }
}

