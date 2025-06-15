package com.challenge.investimentos.investimentos_api.config;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.time.format.DateTimeParseException;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleEnumException(HttpMessageNotReadableException ex) {
        if (ex.getCause() instanceof InvalidFormatException) {
            InvalidFormatException cause = (InvalidFormatException) ex.getCause();
            if (cause.getTargetType().isEnum()) {
                Object[] enumConstants = cause.getTargetType().getEnumConstants();
                String valores = java.util.Arrays.stream(enumConstants)
                        .map(Object::toString)
                        .reduce((a, b) -> a + ", " + b)
                        .orElse("");
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("Valor inválido para o campo TipoInvestimento. " +
                              "Valores permitidos: [" + valores + "].");
            }
            if (cause.getTargetType().equals(java.time.LocalDate.class)) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("Data em formato inválido. Use o formato dd-MM-yyyy.");
            }
            if (cause.getCause() instanceof NumberFormatException) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("Valor numérico inválido em algum campo.");
            }
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Erro na requisição: " + ex.getMessage());
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<String> handleDateParseException(DateTimeParseException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Data em formato inválido. Use o formato dd-MM-yyyy.");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
        String mensagem = ex.getBindingResult().getFieldErrors().stream()
            .map(e -> e.getField() + ": " + e.getDefaultMessage())
            .reduce((a, b) -> a + "; " + b)
            .orElse("Erro de validação nos campos.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagem);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro interno: " + ex.getMessage());
    }
}