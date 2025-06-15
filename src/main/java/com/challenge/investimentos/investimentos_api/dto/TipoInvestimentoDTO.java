package com.challenge.investimentos.investimentos_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO que representa o tipo de investimento associado a um usuário ou operação.
 */
public class TipoInvestimentoDTO {

    /**
     * Tipo do investimento (exemplo: RENDA_FIXA, RENDA_VARIAVEL, etc).
     */
    @Schema(example = "RENDA_FIXA")
    private String tipoInvestimento;

    /**
     * Construtor padrão.
     */
    public TipoInvestimentoDTO() {}

    /**
     * Construtor com parâmetro.
     * @param tipoInvestimento tipo do investimento
     */
    public TipoInvestimentoDTO(String tipoInvestimento) {
        this.tipoInvestimento = tipoInvestimento;
    }

    /**
     * Obtém o tipo de investimento.
     * @return tipo de investimento
     */
    public String getTipoInvestimento() {
        return tipoInvestimento;
    }

    /**
     * Define o tipo de investimento.
     * @param tipoInvestimento tipo de investimento
     */
    public void setTipoInvestimento(String tipoInvestimento) {
        this.tipoInvestimento = tipoInvestimento;
    }
}