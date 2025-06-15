package com.challenge.investimentos.investimentos_api.dto;

public class TipoInvestimentoDTO {
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