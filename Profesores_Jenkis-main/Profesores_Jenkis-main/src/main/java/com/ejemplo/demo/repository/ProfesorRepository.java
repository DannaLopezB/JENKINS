package com.ejemplo.demo.repository;

import com.ejemplo.demo.model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorRepository extends JpaRepository<Profesor, Long> {}
