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
 * Handler global para tratamento de exceções da API de Investimentos.
 * 
 * Intercepta e trata exceções lançadas pelos controllers, retornando
 * respostas HTTP adequadas com mensagens de erro amigáveis ao usuário.
 */
@ControllerAdvice
public class RestExceptionHandler {

    /**
     * Trata erros de deserialização do corpo da requisição JSON.
     * 
     * Captura problemas como: enums inválidos, formatos de data incorretos,
     * valores numéricos mal formatados e outros erros de conversão.
     *
     * @param ex exceção de leitura/conversão do corpo da requisição
     * @return ResponseEntity com mensagem de erro específica e status 400 (Bad Request)
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
     * Trata erros de parsing de datas inválidas.
     *
     * @param ex exceção de formatação de data
     * @return ResponseEntity com mensagem de erro e status 400 (Bad Request)
     */
    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<String> handleDateParseException(DateTimeParseException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Data em formato inválido. Use o formato dd-MM-yyyy.");
    }

    /**
     * Trata erros de validação de campos dos DTOs.
     * 
     * Processa anotações de validação (@NotNull, @Valid, etc.) e retorna
     * mensagens detalhadas para cada campo que falhou na validação.
     *
     * @param ex exceção de validação de argumentos
     * @return ResponseEntity com mensagens de erro por campo e status 400 (Bad Request)
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
     * Fallback para exceções não tratadas pelos outros handlers.
     *
     * @param ex exceção genérica não capturada
     * @return ResponseEntity com mensagem de erro e status 500 (Internal Server Error)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro interno: " + ex.getMessage());
    }
}