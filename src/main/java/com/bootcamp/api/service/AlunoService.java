package com.bootcamp.api.service;

import com.bootcamp.api.model.Aluno;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AlunoService {
    private static final List<Aluno> alunos = new ArrayList<>();
    private static final AtomicInteger counter = new AtomicInteger(1);

    static {
        // Dados iniciais para teste
        alunos.add(new Aluno(counter.getAndIncrement(), "João Silva", "joao@email.com", "2023001"));
        alunos.add(new Aluno(counter.getAndIncrement(), "Maria Souza", "maria@email.com", "2023002"));
    }

    public List<Aluno> listarTodos() {
        return new ArrayList<>(alunos);
    }

    public Aluno cadastrar(Aluno aluno) {
        aluno.setId(counter.getAndIncrement());
        alunos.add(aluno);
        return aluno;
    }
}
