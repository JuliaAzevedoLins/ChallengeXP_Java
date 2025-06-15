package com.challenge.investimentos.investimentos_api.config;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.converter.HttpMessageNotReadableException;

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
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Erro na requisição: " + ex.getMessage());
    }
}