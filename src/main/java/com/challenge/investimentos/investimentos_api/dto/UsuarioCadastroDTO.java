package com.challenge.investimentos.investimentos_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO utilizado para cadastro de um novo usuário investidor.
 * Contém apenas o CPF de identificação do usuário.
 */
public class UsuarioCadastroDTO {

    /**
     * CPF de identificação do usuário investidor.
     */
    @Schema(example = "12345678900")
    private String cpfIdentificacao;

    /**
     * Obtém o CPF de identificação do usuário.
     * @return CPF de identificação
     */
    public String getCpfIdentificacao() {
        return cpfIdentificacao;
    }

    /**
     * Define o CPF de identificação do usuário.
     * @param cpfIdentificacao CPF de identificação
     */
    public void setCpfIdentificacao(String cpfIdentificacao) {
        this.cpfIdentificacao = cpfIdentificacao;
    }
}