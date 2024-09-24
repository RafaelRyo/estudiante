package com.estudiantes.gestion_estudiantes.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name= "estudiante_prueba")
@Data
public class PruebaEstudiante {
    @Id
    private Integer eid;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "especialidad")
    private String especialidad;
    @Column(name = "grado")
    private String grado;
}
