package com.estudiantes.gestion_estudiantes.exception;

import com.estudiantes.gestion_estudiantes.exception.StudentNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleStudentNotFound(StudentNotFoundException ex) {
        log.warn("Estudiante no encontrado: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String field = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(field, message);
        });

        log.warn("Error de validacion en los campos: {}", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, WebRequest request) {

        log.error("Error al parsear el body de la peticion: {}", ex.getMessage());

        String errorMessage = "Error en el formato del JSON enviado";

        if (ex.getCause() instanceof DateTimeParseException) {
            errorMessage = "Formato de fecha incorrecto. Use el formato: 2000-01-15T00:00:00Z o 2000-01-15T00:00:00-05:00";
        } else if (ex.getMessage().contains("OffsetDateTime")) {
            errorMessage = "El campo 'dateBirth' tiene un formato incorrecto. Use: 2000-01-15T00:00:00Z";
        } else if (ex.getMessage().contains("JSON parse error")) {
            errorMessage = "Error al parsear el JSON. Verifique la estructura y tipos de datos";
        }

        log.warn("Respondiendo con error 400: {}", errorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<?> handleDateTimeParseException(
            DateTimeParseException ex, WebRequest request) {

        log.error("Error al parsear fecha: {}", ex.getMessage());
        String errorMessage = "Formato de fecha incorrecto para 'dateBirth'. Use el formato ISO-8601: 2000-01-15T00:00:00Z";

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
        log.error("Error inesperado: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error interno del servidor. Contacte al administrador");
    }
}

