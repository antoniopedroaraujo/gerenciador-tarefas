package com.example.gerenciador_tarefas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> tratarRecursoNaoEncontrado(RecursoNaoEncontradoException ex) {

        Map<String, Object> resposta = Map.of("erro", ex.getMessage(), "timestamp", OffsetDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
    }
}
