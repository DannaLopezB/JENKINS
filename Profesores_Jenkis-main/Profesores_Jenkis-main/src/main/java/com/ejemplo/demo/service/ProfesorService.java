package com.ejemplo.demo.service;

import com.ejemplo.demo.model.Profesor;
import com.ejemplo.demo.repository.ProfesorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorService {

    private final ProfesorRepository profesorRepository;

    public ProfesorService(ProfesorRepository profesorRepository) {
        this.profesorRepository = profesorRepository;
    }

    public List<Profesor> obtenerTodos() {
        return profesorRepository.findAll();
    }

    public Profesor guardar(Profesor profesor) {
        return profesorRepository.save(profesor);
    }
}
