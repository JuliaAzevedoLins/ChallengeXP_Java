package com.challenge.investimentos.investimentos_api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entidade que representa a rentabilidade diária de um {@link Investimento}.
 * 
 * Armazena data, valor diário da ação, taxa diária de rentabilidade e o
 * montante acumulado para o dia.
 */
@Entity
@Table(name = "RENTABILIDADE_DIARIA_TABLE")
public class RentabilidadeDiaria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rentabilidade_diaria_sequence")
    @SequenceGenerator(name = "rentabilidade_diaria_sequence", sequenceName = "RENTABILIDADE_DIARIA_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "DATA_RENTABILIDADE_DIARIA")
    private LocalDate dataRentabilidadeDiaria;

    @Column(name = "VALOR_DIARIO_ACAO", precision = 15, scale = 2)
    private BigDecimal valorDiarioAcao;

    @Column(name = "TAXA_DIARIO_RENTABILIDADE", precision = 10, scale = 4)
    private BigDecimal taxaDiarioRentabilidade;

    @Column(name = "MONTANTE_ACUMULADO_DIARIO", precision = 15, scale = 2)
    private BigDecimal montanteAcumuladoDiario;

    /**
     * Relação N:1 com o investimento ao qual esta rentabilidade pertence.
     * A anotação {@link JsonBackReference} evita recursão na serialização JSON.
     */
    @ManyToOne
    @JsonBackReference
    private Investimento investimento;

    /** Construtor padrão exigido pelo JPA. */
    public RentabilidadeDiaria() {}

    /**
     * Cria uma rentabilidade diária com seus valores principais.
     *
     * @param dataRentabilidadeDiaria data da rentabilidade
     * @param valorDiarioAcao valor diário da ação
     * @param taxaDiarioRentabilidade taxa diária de rentabilidade
     * @param montanteAcumuladoDiario montante acumulado no dia
     */
    public RentabilidadeDiaria(LocalDate dataRentabilidadeDiaria, BigDecimal valorDiarioAcao, BigDecimal taxaDiarioRentabilidade, BigDecimal montanteAcumuladoDiario) {
        this.dataRentabilidadeDiaria = dataRentabilidadeDiaria;
        this.valorDiarioAcao = valorDiarioAcao;
        this.taxaDiarioRentabilidade = taxaDiarioRentabilidade;
        this.montanteAcumuladoDiario = montanteAcumuladoDiario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataRentabilidadeDiaria() {
        return dataRentabilidadeDiaria;
    }

    public void setDataRentabilidadeDiaria(LocalDate dataRentabilidadeDiaria) {
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

    public Investimento getInvestimento() {
        return investimento;
    }

    public void setInvestimento(Investimento investimento) {
        this.investimento = investimento;
    }
}