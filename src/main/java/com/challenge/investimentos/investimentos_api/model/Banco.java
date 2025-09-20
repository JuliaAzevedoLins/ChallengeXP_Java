package com.challenge.investimentos.investimentos_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Embeddable
public class Banco {

    @Column(name = "NOME_BANCO")
    private String nomeBanco;

    @Column(name = "CODIGO_BANCARIO")
    @JsonProperty(access = Access.READ_ONLY) // não enviado pelo cliente
    private Integer codigoBancario;

    public Banco() {}

    public Banco(String nomeBanco, Integer codigoBancario) {
        this.nomeBanco = nomeBanco;
        this.codigoBancario = codigoBancario;
    }

    public String getNomeBanco() {
        return nomeBanco;
    }

    public void setNomeBanco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
    }

    public Integer getCodigoBancario() {
        return codigoBancario;
    }

    public void setCodigoBancario(Integer codigoBancario) {
        this.codigoBancario = codigoBancario;
    }
}
