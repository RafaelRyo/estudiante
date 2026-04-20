package com.estudiantes.gestion_estudiantes.service.impl;

import com.estudiantes.gestion_estudiantes.models.Student;
import com.estudiantes.gestion_estudiantes.repository.PruebaEstudianteRepository;
import com.estudiantes.gestion_estudiantes.service.IPruebaEstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PruebaEstudianteServiceImpl implements IPruebaEstudianteService {

    @Autowired
    private PruebaEstudianteRepository pruebaEstudianteRepository;

    @Override
    public List<Student> searchStudents() {
        return pruebaEstudianteRepository.findAll();
    }

    @Override
    public Optional<Student> obtenerEstudiantePorId(Integer id) {
        return pruebaEstudianteRepository.findById(id);
    }

    @Override
    public Student guardarPruebaEstudiante(Student pruebaEstudiante) {
        return pruebaEstudianteRepository.save(pruebaEstudiante);
    }

    @Override
    public Student actualizarPruebaEstudiante(Integer id, Student pruebaEstudiante) {
        return pruebaEstudianteRepository.findById(id).map(existingEstudiante -> {
            pruebaEstudiante.setEid(existingEstudiante.getEid());
            return pruebaEstudianteRepository.save(pruebaEstudiante);
        }).orElse(null);
    }

    @Override
    public boolean eliminarPruebaEstudiante(Integer id) {
        if(pruebaEstudianteRepository.existsById(id)){
            pruebaEstudianteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
