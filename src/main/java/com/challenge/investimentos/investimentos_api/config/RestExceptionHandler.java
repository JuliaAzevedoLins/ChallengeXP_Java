package com.challenge.investimentos.investimentos_api.config;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.time.format.DateTimeParseException;

/**
 * Handler global para tratamento de exceções na API REST.
 * Fornece respostas amigáveis para erros comuns de validação, formatação e conversão de dados.
 */
@ControllerAdvice
public class RestExceptionHandler {

    /**
     * Trata erros de leitura/conversão do corpo da requisição, como enums inválidos, datas ou números em formato incorreto.
     *
     * @param ex Exceção lançada durante a leitura do corpo da requisição
     * @return ResponseEntity com mensagem de erro e status HTTP apropriado
     */
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

    /**
     * Trata erros de parsing de datas, retornando mensagem clara sobre o formato esperado.
     *
     * @param ex Exceção de formatação de data
     * @return ResponseEntity com mensagem de erro e status 400
     */
    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<String> handleDateParseException(DateTimeParseException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Data em formato inválido. Use o formato dd-MM-yyyy.");
    }

    /**
     * Trata erros de validação de campos dos DTOs, retornando mensagens detalhadas para cada campo inválido.
     *
     * @param ex Exceção de argumento inválido (validação)
     * @return ResponseEntity com mensagem de erro e status 400
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
        String mensagem = ex.getBindingResult().getFieldErrors().stream()
            .map(e -> e.getField() + ": " + e.getDefaultMessage())
            .reduce((a, b) -> a + "; " + b)
            .orElse("Erro de validação nos campos.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagem);
    }

    /**
     * Trata exceções genéricas não capturadas por outros handlers, retornando erro interno do servidor.
     *
     * @param ex Exceção genérica
     * @return ResponseEntity com mensagem de erro e status 500
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro interno: " + ex.getMessage());
    }
}