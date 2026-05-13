package com.estudiantes.gestion_estudiantes.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentRequestDTO {

    @NotBlank(message = "El campo 'name' es obligatorio")
    private String name;

    @NotBlank(message = "El campo 'lastNames' es obligatorio")
    private String lastNames;

    @NotNull(message = "El campo 'dateBirth' es obligatorio")
    private LocalDate dateBirth;

    @NotBlank(message = "El campo 'grade' es obligatorio")
    private String grade;
}

