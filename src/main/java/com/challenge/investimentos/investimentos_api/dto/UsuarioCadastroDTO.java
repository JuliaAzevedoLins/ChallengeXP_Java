package com.challenge.investimentos.investimentos_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class UsuarioCadastroDTO {

    @Schema(example = "12345678900")
    private String cpfIdentificacao;

    public String getCpfIdentificacao() {
        return cpfIdentificacao;
    }

    public void setCpfIdentificacao(String cpfIdentificacao) {
        this.cpfIdentificacao = cpfIdentificacao;
    }
}