package com.challenge.investimentos.investimentos_api.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração do Swagger/OpenAPI para documentação automática da API de Investimentos.
 */
@Configuration
public class SwaggerConfig {

    /**
     * Configura as informações principais da documentação Swagger/OpenAPI.
     *
     * @return OpenAPI com título, descrição e versão da API.
     */
    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Investimentos")
                        .description("API para gerenciamento de investimentos dos usuários")
                        .version("v1.0"));
    }
}