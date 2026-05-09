package com.estudiantes.gestion_estudiantes.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private UUID eid;
    private String name;
    private String lastNames;
    private OffsetDateTime dateBirth;
    private String grade;
}

