package com.example.desafioCadastro.controller;


import com.example.desafioCadastro.service.exception.PetNaoEncontradoException;
import com.example.desafioCadastro.service.exception.RegraDeNegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)//capturar e tratar exceções
    public ResponseEntity<Map<String, Object>> tratarValidacao(MethodArgumentNotValidException ex) {
        Map<String, String> erros = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(erro ->
                erros.put(erro.getField(), erro.getDefaultMessage()));

        return ResponseEntity.badRequest().body(corpoDeErro(HttpStatus.BAD_REQUEST, "Erro de validação", erros));
    }

    @ExceptionHandler(RegraDeNegocioException.class)
    public ResponseEntity<Map<String, Object>> tratarRegraDeNegocio(RegraDeNegocioException ex) {
        return ResponseEntity.badRequest().body(corpoDeErro(HttpStatus.BAD_REQUEST, ex.getMessage(), null));
    }

    @ExceptionHandler(PetNaoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> tratarNaoEncontrado(PetNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(corpoDeErro(HttpStatus.NOT_FOUND, ex.getMessage(), null));
    }

    private Map<String, Object> corpoDeErro(HttpStatus status, String mensagem, Object detalhes) {
        Map<String, Object> corpo = new LinkedHashMap<>();
        corpo.put("timestamp", LocalDateTime.now());
        corpo.put("status", status.value());
        corpo.put("mensagem", mensagem);
        if (detalhes != null) {
            corpo.put("detalhes", detalhes);
        }
        return corpo;
    }
}
