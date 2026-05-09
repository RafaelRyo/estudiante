package com.estudiantes.gestion_estudiantes.infrastructure.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface StudentJpaRepository extends JpaRepository<StudentEntity, UUID> {
}

