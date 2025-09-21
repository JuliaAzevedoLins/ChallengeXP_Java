package com.challenge.investimentos.investimentos_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import com.challenge.investimentos.investimentos_api.model.RentabilidadeDiaria;
import java.time.format.DateTimeFormatter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/**
 * DTO que representa os dados de rentabilidade diária de um investimento.
 * Inclui data, valor diário da ação, taxa diária e montante acumulado diário.
 */
public class RentabilidadeDiariaDTO {

    /** Data da rentabilidade diária no formato "dd-MM-yyyy". */
    @Schema(example = "01-01-2025")
    @NotBlank(message = "dataRentabilidadeDiaria é obrigatória")
    @Pattern(regexp = "^\\d{2}-\\d{2}-\\d{4}$", message = "dataRentabilidadeDiaria deve estar no formato dd-MM-yyyy")
    private String dataRentabilidadeDiaria;

    /** Valor diário da ação. */
    @Schema(example = "100.50")
    @NotNull(message = "valorDiarioAcao é obrigatório")
    private BigDecimal valorDiarioAcao;

    /** Taxa de rentabilidade diária. */
    @Schema(example = "0.12")
    @NotNull(message = "taxaDiarioRentabilidade é obrigatório")
    private BigDecimal taxaDiarioRentabilidade;

    /** Montante acumulado diário. */
    @Schema(example = "1050.75")
    @NotNull(message = "montanteAcumuladoDiario é obrigatório")
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

    /** Mapper: entidade -> DTO */
    public static RentabilidadeDiariaDTO fromEntity(RentabilidadeDiaria rd) {
        if (rd == null) return null;
        RentabilidadeDiariaDTO dto = new RentabilidadeDiariaDTO();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        dto.setDataRentabilidadeDiaria(rd.getDataRentabilidadeDiaria() != null ? rd.getDataRentabilidadeDiaria().format(fmt) : null);
        dto.setValorDiarioAcao(rd.getValorDiarioAcao());
        dto.setTaxaDiarioRentabilidade(rd.getTaxaDiarioRentabilidade());
        dto.setMontanteAcumuladoDiario(rd.getMontanteAcumuladoDiario());
        return dto;
    }
}
