package com.estudiantes.gestion_estudiantes.exception;

import java.util.UUID;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(UUID id) {
        super("Estudiante con id " + id + " no encontrado");
    }
}
