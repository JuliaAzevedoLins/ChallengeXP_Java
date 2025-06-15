package com.challenge.investimentos.investimentos_api.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Entidade que representa a rentabilidade diária de um investimento.
 * Contém informações sobre a data, valor diário da ação, taxa de rentabilidade diária,
 * montante acumulado diário e a relação com o investimento.
 */
@Entity
@Table(name = "RENTABILIDADE_DIARIA")
public class RentabilidadeDiaria {

    /** Identificador único da rentabilidade diária. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Investimento associado a esta rentabilidade diária. */
    @ManyToOne
    @JoinColumn(name = "INVESTIMENTO_ID")
    @JsonBackReference
    private Investimento investimento;

    /** Data da rentabilidade diária. */
    @Column(name = "DATA_RENTABILIDADE_DIARIA")
    private LocalDate dataRentabilidadeDiaria;

    /** Valor diário da ação. */
    @Column(name = "VALOR_DIARIO_ACAO")
    private Double valorDiarioAcao;

    /** Taxa de rentabilidade diária. */
    @Column(name = "TAXA_DIARIO_RENTABILIDADE")
    private String taxaDiarioRentabilidade;

    /** Montante acumulado diário. */
    @Column(name = "MONTANTE_ACUMULADO_DIARIO")
    private Double montanteAcumuladoDiario;

    /**
     * Obtém o identificador da rentabilidade diária.
     * @return id da rentabilidade diária
     */
    public Long getId() {
        return id;
    }

    /**
     * Obtém o investimento associado.
     * @return investimento associado
     */
    public Investimento getInvestimento() {
        return investimento;
    }

    /**
     * Define o investimento associado.
     * @param investimento investimento associado
     */
    public void setInvestimento(Investimento investimento) {
        this.investimento = investimento;
    }

    /**
     * Obtém a data da rentabilidade diária.
     * @return data da rentabilidade diária
     */
    public LocalDate getDataRentabilidadeDiaria() {
        return dataRentabilidadeDiaria;
    }

    /**
     * Define a data da rentabilidade diária.
     * @param dataRentabilidadeDiaria data da rentabilidade diária
     */
    public void setDataRentabilidadeDiaria(LocalDate dataRentabilidadeDiaria) {
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