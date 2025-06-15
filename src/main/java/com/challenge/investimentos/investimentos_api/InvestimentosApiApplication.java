package com.challenge.investimentos.investimentos_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal responsável por inicializar a aplicação Spring Boot da API de Investimentos.
 */
@SpringBootApplication
public class InvestimentosApiApplication {

    /**
     * Método principal que inicia a aplicação.
     * @param args argumentos de linha de comando
     */
    public static void main(String[] args) {
        SpringApplication.run(InvestimentosApiApplication.class, args);
    }
}