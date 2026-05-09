package com.estudiantes.gestion_estudiantes.domain.port.out;

import com.estudiantes.gestion_estudiantes.domain.model.Student;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentOutputPort {
    List<Student> findAll();
    Optional<Student> findById(UUID id);
    Student save(Student student);
    void deleteById(UUID id);
    boolean existsById(UUID id);
}
