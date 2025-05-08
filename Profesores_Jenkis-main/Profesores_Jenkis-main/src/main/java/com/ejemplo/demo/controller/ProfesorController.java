package com.ejemplo.demo.controller;

import com.ejemplo.demo.model.Profesor;
import com.ejemplo.demo.service.ProfesorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profesores")
public class ProfesorController {

    private final ProfesorService profesorService;

    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    @GetMapping
    public List<Profesor> listar() {
        return profesorService.obtenerTodos();
    }

    @PostMapping
    public Profesor agregar(@RequestBody Profesor profesor) {
        return profesorService.guardar(profesor);
    }
}
