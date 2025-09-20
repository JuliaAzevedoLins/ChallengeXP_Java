package com.challenge.investimentos.investimentos_api.dto;

import java.math.BigDecimal;
import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO que representa os dados de um investimento.
 */
public class InvestimentoDTO {

    @Schema(example = "C6 Bank")
    private String nomeBanco;

    @Schema(example = "RENDA_FIXA")
    private String tipoInvestimento;

    @Schema(example = "CDB 2025")
    private String nomeInvestimento;

    @Schema(example = "1000.50")
    private BigDecimal montanteInicial;

    @Schema(example = "100.50")
    private BigDecimal valorInicialAcao;

    @Schema(example = "0.05")
    private BigDecimal taxaRentabilidade;

    @Schema(example = "10")
    private Integer numeroAcoesInicial;

    @Schema(description = "Lista de rentabilidades diárias associadas ao investimento")
    private List<RentabilidadeDiariaDTO> rentabilidadeDiaria;

    // Getters e Setters
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
}
