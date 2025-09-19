package com.challenge.investimentos.investimentos_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Banco implements Serializable {

    @Column(name = "NOME_BANCO")
    private String nomeBanco;

    @Column(name = "CODIGO_BANCARIO")
    private Integer codigoBancario;

    public Banco() {}

    public Banco(String nomeBanco, Integer codigoBancario) {
        this.nomeBanco = nomeBanco;
        this.codigoBancario = codigoBancario;
    }

    // Getters
    public String getNomeBanco() {
        return nomeBanco;
    }

    public Integer getCodigoBancario() {
        return codigoBancario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Banco banco = (Banco) o;
        return Objects.equals(nomeBanco, banco.nomeBanco) && Objects.equals(codigoBancario, banco.codigoBancario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeBanco, codigoBancario);
    }
}