package com.challenge.investimentos.investimentos_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO que representa os dados de rentabilidade diária de um investimento.
 */
public class RentabilidadeDiariaDTO {

    /** Data da rentabilidade diária no formato "dd-MM-yyyy". */
    @Schema(example = "01-01-2025")
    private String dataRentabilidadeDiaria;

    /** Valor diário da ação. */
    private Double valorDiarioAcao;

    /** Taxa de rentabilidade diária. */
    private String taxaDiarioRentabilidade;

    /** Montante acumulado diário. */
    private Double montanteAcumuladoDiario;

    /**
     * Obtém a data da rentabilidade diária.
     * @return data da rentabilidade diária
     */
    public String getDataRentabilidadeDiaria() {
        return dataRentabilidadeDiaria;
    }

    /**
     * Define a data da rentabilidade diária.
     * @param dataRentabilidadeDiaria data da rentabilidade diária
     */
    public void setDataRentabilidadeDiaria(String dataRentabilidadeDiaria) {
        this.dataRentabilidadeDiaria = dataRentabilidadeDiaria;
    }

    /**
     * Obtém o valor diário da ação.
     * @return valor diário da ação
     */
    public Double getValorDiarioAcao() {
        return valorDiarioAcao;
    }

    /**
     * Define o valor diário da ação.
     * @param valorDiarioAcao valor diário da ação
     */
    public void setValorDiarioAcao(Double valorDiarioAcao) {
        this.valorDiarioAcao = valorDiarioAcao;
    }

    /**
     * Obtém a taxa de rentabilidade diária.
     * @return taxa de rentabilidade diária
     */
    public String getTaxaDiarioRentabilidade() {
        return taxaDiarioRentabilidade;
    }

    /**
     * Define a taxa de rentabilidade diária.
     * @param taxaDiarioRentabilidade taxa de rentabilidade diária
     */
    public void setTaxaDiarioRentabilidade(String taxaDiarioRentabilidade) {
        this.taxaDiarioRentabilidade = taxaDiarioRentabilidade;
    }

    /**
     * Obtém o montante acumulado diário.
     * @return montante acumulado diário
     */
    public Double getMontanteAcumuladoDiario() {
        return montanteAcumuladoDiario;
    }

    /**
     * Define o montante acumulado diário.
     * @param montanteAcumuladoDiario montante acumulado diário
     */
    public void setMontanteAcumuladoDiario(Double montanteAcumuladoDiario) {
        this.montanteAcumuladoDiario = montanteAcumuladoDiario;
    }
}