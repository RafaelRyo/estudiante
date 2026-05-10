package com.estudiantes.gestion_estudiantes.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private UUID eid;
    private String name;
    private String lastNames;
    private LocalDate dateBirth;
    private String grade;
}

