package com.estudiantes.gestion_estudiantes.infrastructure.mapper;

import com.estudiantes.gestion_estudiantes.domain.model.Student;
import com.estudiantes.gestion_estudiantes.infrastructure.adapter.out.persistence.StudentEntity;
import org.springframework.stereotype.Component;

@Component
public class StudentEntityMapper {

    public Student toDomain(StudentEntity entity) {
        return Student.builder()
                .eid(entity.getEid())
                .name(entity.getName())
                .lastNames(entity.getLastNames())
                .dateBirth(entity.getDateBirth())
                .grade(entity.getGrade())
                .build();
    }

    public StudentEntity toEntity(Student domain) {
        return StudentEntity.builder()
                .eid(domain.getEid())
                .name(domain.getName())
                .lastNames(domain.getLastNames())
                .dateBirth(domain.getDateBirth())
                .grade(domain.getGrade())
                .build();
    }
}

