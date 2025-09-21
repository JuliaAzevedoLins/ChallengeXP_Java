package com.challenge.investimentos.investimentos_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * DTO utilizado para cadastro de um novo usuário investidor.
 * Contém apenas o CPF de identificação do usuário.
 */
public class UsuarioCadastroDTO {

    /**
     * CPF de identificação do usuário investidor.
     */
    @Schema(example = "12345678900")
    @NotBlank(message = "cpfIdentificacao é obrigatório")
    @Size(min = 11, max = 14, message = "cpfIdentificacao deve ter entre 11 e 14 caracteres (apenas dígitos ou com máscara)")
    @Pattern(regexp = "^\\d{11}$|^(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2})$", message = "cpfIdentificacao deve ser 11 dígitos ou no formato XXX.XXX.XXX-XX")
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