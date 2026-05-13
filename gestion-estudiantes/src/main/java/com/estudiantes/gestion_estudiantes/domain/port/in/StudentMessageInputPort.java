package com.estudiantes.gestion_estudiantes.domain.port.in;
import com.estudiantes.gestion_estudiantes.application.dto.StudentRequestDTO;
public interface StudentMessageInputPort {
    void processStudentMessage(StudentRequestDTO dto);
}