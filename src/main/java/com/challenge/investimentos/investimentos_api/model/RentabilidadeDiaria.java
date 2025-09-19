// src/main/java/com/challenge/investimentos/investimentos_api/model/RentabilidadeDiaria.java
package com.challenge.investimentos.investimentos_api.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.io.Serializable;

// A anotação @Entity é removida pois agora é um Value Object.
// Agora ela deve ser @Embeddable
@Embeddable
public class RentabilidadeDiaria implements Serializable {

    // O campo ID foi removido.
    // O campo @ManyToOne com @JsonBackReference também foi removido, pois não haverá mais uma relação de entidade.

    @Column(name = "DATA_RENTABILIDADE_DIARIA")
    private LocalDate dataRentabilidadeDiaria;

    @Column(name = "VALOR_DIARIO_ACAO")
    private Double valorDiarioAcao;

    @Column(name = "TAXA_DIARIO_RENTABILIDADE")
    private String taxaDiarioRentabilidade;

    @Column(name = "MONTANTE_ACUMULADO_DIARIO")
    private Double montanteAcumuladoDiario;

    public RentabilidadeDiaria() {}
    
    // Construtor completo para imutabilidade
    public RentabilidadeDiaria(LocalDate dataRentabilidadeDiaria, Double valorDiarioAcao, String taxaDiarioRentabilidade, Double montanteAcumuladoDiario) {
        this.dataRentabilidadeDiaria = dataRentabilidadeDiaria;
        this.valorDiarioAcao = valorDiarioAcao;
        this.taxaDiarioRentabilidade = taxaDiarioRentabilidade;
        this.montanteAcumuladoDiario = montanteAcumuladoDiario;
    }

    // Apenas getters são necessários, pois o objeto é imutável
    public LocalDate getDataRentabilidadeDiaria() {
        return dataRentabilidadeDiaria;
    }

    public Double getValorDiarioAcao() {
        return valorDiarioAcao;
    }

    public String getTaxaDiarioRentabilidade() {
        return taxaDiarioRentabilidade;
    }

    public Double getMontanteAcumuladoDiario() {
        return montanteAcumuladoDiario;
    }
}