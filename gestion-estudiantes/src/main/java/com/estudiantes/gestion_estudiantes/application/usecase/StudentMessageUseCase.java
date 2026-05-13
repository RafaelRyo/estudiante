package com.estudiantes.gestion_estudiantes.application.usecase;
import com.estudiantes.gestion_estudiantes.application.dto.StudentRequestDTO;
import com.estudiantes.gestion_estudiantes.domain.port.in.StudentInputPort;
import com.estudiantes.gestion_estudiantes.domain.port.in.StudentMessageInputPort;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.stream.Collectors;
@Slf4j
@Service
@RequiredArgsConstructor
public class StudentMessageUseCase implements StudentMessageInputPort {
    private final StudentInputPort studentInputPort;
    private final Validator validator;
    @Override
    @Retryable(
        retryFor = Exception.class,
        exclude = IllegalArgumentException.class,
        maxAttempts = 3,
        backoff = @Backoff(delay = 2000, multiplier = 2)
    )
    public void processStudentMessage(StudentRequestDTO dto) {
        log.info("[SQS] Procesando mensaje para estudiante: {} {}", dto.getName(), dto.getLastNames());
        Set<ConstraintViolation<StudentRequestDTO>> violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            String errors = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(", "));
            log.error("[SQS] Mensaje invalido, no se reintenta. Errores: {}", errors);
            throw new IllegalArgumentException("Mensaje SQS invalido: " + errors);
        }
        studentInputPort.createStudent(dto);
        log.info("[SQS] Estudiante registrado en DB: {} {}", dto.getName(), dto.getLastNames());
    }
}
