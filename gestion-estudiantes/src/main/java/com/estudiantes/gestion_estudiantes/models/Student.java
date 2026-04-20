package com.estudiantes.gestion_estudiantes.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name= "estudiante")
@Data
public class Estudiante {
    @Id
    private Integer eid;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "fec")
    @Column(name = "especialidad")
    private String especialidad;
    @Column(name = "grado")
    private String grado;
}
