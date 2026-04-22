package com.estudiantes.gestion_estudiantes.service.impl;

import com.estudiantes.gestion_estudiantes.models.Student;
import com.estudiantes.gestion_estudiantes.service.IStudentService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements IStudentService {

    @Override
    public List<Student> searchStudents() {
        return Collections.emptyList();
    }

    @Override
    public Optional<Student> obtenerEstudiantePorId(Integer id) {
        return Optional.empty();
    }

    @Override
    public Student guardarPruebaEstudiante(Student pruebaEstudiante) {
        return null;
    }

    @Override
    public Student actualizarPruebaEstudiante(Integer id, Student pruebaEstudiante) {
        return null;
    }

    @Override
    public boolean eliminarPruebaEstudiante(Integer id) {
        return false;
    }
}
