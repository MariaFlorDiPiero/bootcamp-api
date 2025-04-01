package com.bootcamp.api;

import com.bootcamp.api.handler.AlunoHandler;
import com.bootcamp.api.handler.CursoHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws IOException {
        // Cria o servidor HTTP na porta 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        
        // Configura os contextos (endpoints)
        server.createContext("/alunos", new AlunoHandler());
        server.createContext("/cursos", new CursoHandler());
        
        // Define um executor padrão e inicia o servidor
        server.setExecutor(null);
        server.start();
        
        System.out.println("Servidor iniciado na porta 8080");
        System.out.println("Endpoints disponíveis:");
        System.out.println("GET  /alunos - Listar todos os alunos");
        System.out.println("POST /alunos - Cadastrar novo aluno");
        System.out.println("GET  /cursos - Listar todos os cursos");
        System.out.println("POST /cursos - Criar novo curso");
    }
}