package com.challenge.investimentos.investimentos_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class TipoInvestimentoDTO {

    @Schema(example = "RENDA_FIXA")
    private String tipoInvestimento;

    public TipoInvestimentoDTO() {}

    public TipoInvestimentoDTO(String tipoInvestimento) {
        this.tipoInvestimento = tipoInvestimento;
    }

    public String getTipoInvestimento() {
        return tipoInvestimento;
    }

    public void setTipoInvestimento(String tipoInvestimento) {
        this.tipoInvestimento = tipoInvestimento;
    }
}