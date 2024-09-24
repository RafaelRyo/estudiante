package com.estudiantes.gestion_estudiantes.service;

import com.estudiantes.gestion_estudiantes.models.PruebaEstudiante;
import com.estudiantes.gestion_estudiantes.repository.PruebaEstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PruebaEstudianteServiceImpl implements IPruebaEstudianteService{

    @Autowired
    private PruebaEstudianteRepository pruebaEstudianteRepository;

    @Override
    public List<PruebaEstudiante> obtenerPruebaEstudiantes() {
        return pruebaEstudianteRepository.findAll();
    }

    @Override
    public Optional<PruebaEstudiante> obtenerEstudiantePorId(Integer id) {
        return pruebaEstudianteRepository.findById(id);
    }

    @Override
    public PruebaEstudiante guardarPruebaEstudiante(PruebaEstudiante pruebaEstudiante) {
        return pruebaEstudianteRepository.save(pruebaEstudiante);
    }

    @Override
    public PruebaEstudiante actualizarPruebaEstudiante(Integer id, PruebaEstudiante pruebaEstudiante) {
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
