package com.challenge.investimentos.investimentos_api.dto;

import java.math.BigDecimal;
import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;
import com.challenge.investimentos.investimentos_api.model.Investimento;
import java.util.stream.Collectors;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

/**
 * DTO que representa os dados de um investimento.
 */
public class InvestimentoDTO {
    @Schema(example = "1")
    private Long id;

    @Schema(example = "C6 Bank")
    private String nomeBanco;

    @Schema(example = "RENDA_FIXA")
    private String tipoInvestimento;

    @Schema(example = "CDB 2025")
    @NotBlank(message = "nomeInvestimento é obrigatório")
    private String nomeInvestimento;

    @Schema(example = "1000.50")
    @NotNull(message = "montanteInicial é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "montanteInicial deve ser maior que zero")
    private BigDecimal montanteInicial;

    @Schema(example = "100.50")
    @NotNull(message = "valorInicialAcao é obrigatório")
    @DecimalMin(value = "0.0", inclusive = true, message = "valorInicialAcao não pode ser negativo")
    private BigDecimal valorInicialAcao;

    @Schema(example = "0.05")
    @NotNull(message = "taxaRentabilidade é obrigatório")
    private BigDecimal taxaRentabilidade;

    @Schema(example = "10")
    @NotNull(message = "numeroAcoesInicial é obrigatório")
    @PositiveOrZero(message = "numeroAcoesInicial deve ser zero ou maior")
    private Integer numeroAcoesInicial;

    @Schema(description = "Lista de rentabilidades diárias associadas ao investimento")
    @Valid
    private List<RentabilidadeDiariaDTO> rentabilidadeDiaria;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeBanco() {
        return nomeBanco;
    }

    public void setNomeBanco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
    }

    public String getTipoInvestimento() {
        return tipoInvestimento;
    }

    public void setTipoInvestimento(String tipoInvestimento) {
        this.tipoInvestimento = tipoInvestimento;
    }

    public String getNomeInvestimento() {
        return nomeInvestimento;
    }

    public void setNomeInvestimento(String nomeInvestimento) {
        this.nomeInvestimento = nomeInvestimento;
    }

    public BigDecimal getMontanteInicial() {
        return montanteInicial;
    }

    public void setMontanteInicial(BigDecimal montanteInicial) {
        this.montanteInicial = montanteInicial;
    }

    public BigDecimal getValorInicialAcao() {
        return valorInicialAcao;
    }

    public void setValorInicialAcao(BigDecimal valorInicialAcao) {
        this.valorInicialAcao = valorInicialAcao;
    }

    public BigDecimal getTaxaRentabilidade() {
        return taxaRentabilidade;
    }

    public void setTaxaRentabilidade(BigDecimal taxaRentabilidade) {
        this.taxaRentabilidade = taxaRentabilidade;
    }

    public Integer getNumeroAcoesInicial() {
        return numeroAcoesInicial;
    }

    public void setNumeroAcoesInicial(Integer numeroAcoesInicial) {
        this.numeroAcoesInicial = numeroAcoesInicial;
    }

    public List<RentabilidadeDiariaDTO> getRentabilidadeDiaria() {
        return rentabilidadeDiaria;
    }

    public void setRentabilidadeDiaria(List<RentabilidadeDiariaDTO> rentabilidadeDiaria) {
        this.rentabilidadeDiaria = rentabilidadeDiaria;
    }

    /** Mapper: entidade -> DTO */
    public static InvestimentoDTO fromEntity(Investimento inv) {
        if (inv == null) return null;
        InvestimentoDTO dto = new InvestimentoDTO();
        dto.setId(inv.getId());
        dto.setNomeBanco(inv.getNomeBanco());
        dto.setTipoInvestimento(inv.getTipoInvestimento() != null ? inv.getTipoInvestimento().name() : null);
        dto.setNomeInvestimento(inv.getNomeInvestimento());
        dto.setMontanteInicial(inv.getMontanteInicial());
        dto.setValorInicialAcao(inv.getValorInicialAcao());
        dto.setTaxaRentabilidade(inv.getTaxaRentabilidade());
        dto.setNumeroAcoesInicial(inv.getNumeroAcoesInicial());
        if (inv.getRentabilidadeDiaria() != null) {
            dto.setRentabilidadeDiaria(inv.getRentabilidadeDiaria().stream()
                .map(RentabilidadeDiariaDTO::fromEntity)
                .collect(Collectors.toList()));
        }
        return dto;
    }
}
