package com.bootcamp.api.service;

import com.bootcamp.api.model.Curso;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CursoService {
    private static final List<Curso> cursos = new ArrayList<>();
    private static final AtomicInteger counter = new AtomicInteger(1);

    static {
        // Dados iniciais para teste
        cursos.add(new Curso(counter.getAndIncrement(), "Java Básico", "Introdução à linguagem Java", 40));
        cursos.add(new Curso(counter.getAndIncrement(), "Spring Boot", "Desenvolvimento de APIs com Spring", 60));
    }

    public List<Curso> listarTodos() {
        return new ArrayList<>(cursos);
    }

    public Curso criar(Curso curso) {
        curso.setId(counter.getAndIncrement());
        cursos.add(curso);
        return curso;
    }
}