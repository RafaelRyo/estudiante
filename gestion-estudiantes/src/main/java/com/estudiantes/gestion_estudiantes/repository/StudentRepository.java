package com.estudiantes.gestion_estudiantes.repository;

import com.estudiantes.gestion_estudiantes.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
