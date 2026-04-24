package com.estudiantes.gestion_estudiantes.models;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class Student {
    private Integer eid;
    private String name;
    private String lastNames;
    private OffsetDateTime dateBirth;
    private String grade;
}