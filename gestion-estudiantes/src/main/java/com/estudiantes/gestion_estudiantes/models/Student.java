package com.estudiantes.gestion_estudiantes.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.OffsetDateTime;

@Entity
@Table(name= "student")
@Data
public class Student {
    @Id
    private Integer eid;
    @Column(name = "name")
    private String name;
    @Column(name = "lastNames")
    private String lastNames;
    @Column(name = "dateBirth")
    private OffsetDateTime dateBirth;
    @Column(name = "grado")
    private String grado;
}
