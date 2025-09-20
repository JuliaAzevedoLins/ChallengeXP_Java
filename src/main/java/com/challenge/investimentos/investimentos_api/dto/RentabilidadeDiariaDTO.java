package com.challenge.investimentos.investimentos_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

/**
 * DTO que representa os dados de rentabilidade diária de um investimento.
 * Inclui data, valor diário da ação, taxa diária e montante acumulado diário.
 */
public class RentabilidadeDiariaDTO {

    /** Data da rentabilidade diária no formato "dd-MM-yyyy". */
    @Schema(example = "01-01-2025")
    private String dataRentabilidadeDiaria;

    /** Valor diário da ação. */
    @Schema(example = "100.50")
    private BigDecimal valorDiarioAcao;

    /** Taxa de rentabilidade diária. */
    @Schema(example = "0.12")
    private BigDecimal taxaDiarioRentabilidade;

    /** Montante acumulado diário. */
    @Schema(example = "1050.75")
    private BigDecimal montanteAcumuladoDiario;

    // Getters e Setters
    public String getDataRentabilidadeDiaria() {
        return dataRentabilidadeDiaria;
    }

    public void setDataRentabilidadeDiaria(String dataRentabilidadeDiaria) {
        this.dataRentabilidadeDiaria = dataRentabilidadeDiaria;
    }

    public BigDecimal getValorDiarioAcao() {
        return valorDiarioAcao;
    }

    public void setValorDiarioAcao(BigDecimal valorDiarioAcao) {
        this.valorDiarioAcao = valorDiarioAcao;
    }

    public BigDecimal getTaxaDiarioRentabilidade() {
        return taxaDiarioRentabilidade;
    }

    public void setTaxaDiarioRentabilidade(BigDecimal taxaDiarioRentabilidade) {
        this.taxaDiarioRentabilidade = taxaDiarioRentabilidade;
    }

    public BigDecimal getMontanteAcumuladoDiario() {
        return montanteAcumuladoDiario;
    }

    public void setMontanteAcumuladoDiario(BigDecimal montanteAcumuladoDiario) {
        this.montanteAcumuladoDiario = montanteAcumuladoDiario;
    }
}
