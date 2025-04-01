package com.bootcamp.api.handler;

import com.bootcamp.api.model.Aluno;
import com.bootcamp.api.service.AlunoService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class AlunoHandler implements HttpHandler {
    private final AlunoService alunoService = new AlunoService();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "";
        int statusCode = 200;

        try {
            String method = exchange.getRequestMethod();
            String path = exchange.getRequestURI().getPath();

            if ("GET".equals(method) && "/alunos".equals(path)) {
                // GET /alunos - Listar todos os alunos
                List<Aluno> alunos = alunoService.listarTodos();
                response = toJson(alunos);
            } else if ("POST".equals(method) && "/alunos".equals(path)) {
                // POST /alunos - Cadastrar novo aluno
                InputStream is = exchange.getRequestBody();
                String requestBody = new String(is.readAllBytes(), StandardCharsets.UTF_8);
                Aluno aluno = fromJson(requestBody, Aluno.class);
                Aluno alunoCadastrado = alunoService.cadastrar(aluno);
                response = toJson(alunoCadastrado);
            } else {
                statusCode = 404;
                response = "Endpoint não encontrado";
            }
        } catch (Exception e) {
            statusCode = 500;
            response = "Erro interno: " + e.getMessage();
        }

        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(statusCode, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private String toJson(Object obj) {
        // Implementação simplificada de conversão para JSON
        if (obj instanceof List) {
            StringBuilder sb = new StringBuilder("[");
            for (Object item : (List<?>) obj) {
                sb.append(toJson(item)).append(",");
            }
            if (!((List<?>) obj).isEmpty()) {
                sb.deleteCharAt(sb.length() - 1);
            }
            return sb.append("]").toString();
        } else if (obj instanceof Aluno) {
            Aluno a = (Aluno) obj;
            return String.format(
                "{\"id\":%d,\"nome\":\"%s\",\"email\":\"%s\",\"matricula\":\"%s\"}",
                a.getId(), a.getNome(), a.getEmail(), a.getMatricula()
            );
        }
        return "{}";
    }

    private Aluno fromJson(String json, Class<Aluno> clazz) {
        // Implementação simplificada de conversão de JSON
        json = json.replaceAll("[{}\"]", "");
        String[] parts = json.split(",");
        Aluno aluno = new Aluno();
        for (String part : parts) {
            String[] keyValue = part.split(":");
            String key = keyValue[0].trim();
            String value = keyValue[1].trim();
            
            switch (key) {
                case "nome": aluno.setNome(value); break;
                case "email": aluno.setEmail(value); break;
                case "matricula": aluno.setMatricula(value); break;
            }
        }
        return aluno;
    }
}
