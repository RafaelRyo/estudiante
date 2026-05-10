package com.estudiantes.gestion_estudiantes.application.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class StudentResponseDTO {
    private UUID eid;
    private String name;
    private String lastNames;
    private LocalDate dateBirth;
    private String grade;
}

