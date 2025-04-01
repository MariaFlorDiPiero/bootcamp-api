package com.bootcamp.api.handler;

import com.bootcamp.api.model.Curso;
import com.bootcamp.api.service.CursoService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CursoHandler implements HttpHandler {
    private final CursoService cursoService = new CursoService();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "";
        int statusCode = 200;

        try {
            String method = exchange.getRequestMethod();
            String path = exchange.getRequestURI().getPath();

            if ("GET".equals(method) && "/cursos".equals(path)) {
                // GET /cursos - Listar todos os cursos
                List<Curso> cursos = cursoService.listarTodos();
                response = toJson(cursos);
            } else if ("POST".equals(method) && "/cursos".equals(path)) {
                // POST /cursos - Criar novo curso
                InputStream is = exchange.getRequestBody();
                String requestBody = new String(is.readAllBytes(), StandardCharsets.UTF_8);
                Curso curso = fromJson(requestBody, Curso.class);
                Curso cursoCriado = cursoService.criar(curso);
                response = toJson(cursoCriado);
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
        } else if (obj instanceof Curso) {
            Curso c = (Curso) obj;
            return String.format(
                "{\"id\":%d,\"nome\":\"%s\",\"descricao\":\"%s\",\"cargaHoraria\":%d}",
                c.getId(), c.getNome(), c.getDescricao(), c.getCargaHoraria()
            );
        }
        return "{}";
    }

    private Curso fromJson(String json, Class<Curso> clazz) {
        // Implementação simplificada de conversão de JSON
        json = json.replaceAll("[{}\"]", "");
        String[] parts = json.split(",");
        Curso curso = new Curso();
        for (String part : parts) {
            String[] keyValue = part.split(":");
            String key = keyValue[0].trim();
            String value = keyValue[1].trim();
            
            switch (key) {
                case "nome": curso.setNome(value); break;
                case "descricao": curso.setDescricao(value); break;
                case "cargaHoraria": curso.setCargaHoraria(Integer.parseInt(value)); break;
            }
        }
        return curso;
    }
}
