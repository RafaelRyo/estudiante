package com.estudiantes.gestion_estudiantes.infrastructure.adapter.out.persistence;

import com.estudiantes.gestion_estudiantes.domain.model.Student;
import com.estudiantes.gestion_estudiantes.domain.port.out.StudentOutputPort;
import com.estudiantes.gestion_estudiantes.infrastructure.mapper.StudentEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StudentPersistenceAdapter implements StudentOutputPort {

    private final StudentJpaRepository jpaRepository;
    private final StudentEntityMapper mapper;

    @Override
    public List<Student> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Student> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Student save(Student student) {
        StudentEntity entity = mapper.toEntity(student);
        return mapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(UUID id) {
        return jpaRepository.existsById(id);
    }
}
