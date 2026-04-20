package com.estudiantes.gestion_estudiantes.service;

import com.estudiantes.gestion_estudiantes.models.Student;

import java.util.List;
import java.util.Optional;

public interface IPruebaEstudianteService {
    List<Student> searchStudents();
    Optional<Student> obtenerEstudiantePorId(Integer id);
    Student guardarPruebaEstudiante(Student pruebaEstudiante);
    Student actualizarPruebaEstudiante(Integer id, Student pruebaEstudiante);
    boolean eliminarPruebaEstudiante(Integer id);
}
