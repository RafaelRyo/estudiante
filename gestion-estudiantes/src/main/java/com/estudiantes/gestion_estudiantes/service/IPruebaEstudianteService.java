package com.estudiantes.gestion_estudiantes.service;

import com.estudiantes.gestion_estudiantes.models.PruebaEstudiante;

import java.util.List;
import java.util.Optional;

public interface IPruebaEstudianteService {
    List<PruebaEstudiante> obtenerPruebaEstudiantes();
    Optional<PruebaEstudiante> obtenerEstudiantePorId(Integer id);
    PruebaEstudiante guardarPruebaEstudiante(PruebaEstudiante pruebaEstudiante);
    PruebaEstudiante actualizarPruebaEstudiante(Integer id, PruebaEstudiante pruebaEstudiante);
    boolean eliminarPruebaEstudiante(Integer id);
}
