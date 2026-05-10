package com.estudiantes.gestion_estudiantes.infrastructure.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(length = 36, nullable = false, updatable = false)
    private UUID eid;

    @Column(nullable = false)
    private String name;

    @Column(name = "last_names", nullable = false)
    private String lastNames;

    @Column(name = "date_birth", nullable = false)
    private LocalDate dateBirth;

    @Column(nullable = false)
    private String grade;
}

