package com.challenge.investimentos.investimentos_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

/**
 * Valor-objeto incorporável que representa um banco associado a um investidor.
 * 
 * Contém o nome do banco e o código bancário (somente leitura na API).
 */
@Embeddable
public class Banco {

    @Column(name = "NOME_BANCO")
    private String nomeBanco;

    @Column(name = "CODIGO_BANCARIO")
    @JsonProperty(access = Access.READ_ONLY)
    private Integer codigoBancario;

    /**
     * Construtor padrão exigido pelo JPA.
     */
    public Banco() {}

    /**
     * Constrói um objeto Banco.
     * 
     * @param nomeBanco nome do banco
     * @param codigoBancario código identificador do banco
     */
    public Banco(String nomeBanco, Integer codigoBancario) {
        this.nomeBanco = nomeBanco;
        this.codigoBancario = codigoBancario;
    }

    /**
     * Obtém o nome do banco.
     * @return nome do banco
     */
    public String getNomeBanco() {
        return nomeBanco;
    }

    /**
     * Define o nome do banco.
     * @param nomeBanco nome do banco
     */
    public void setNomeBanco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
    }

    /**
     * Obtém o código bancário (somente leitura na API).
     * @return código bancário
     */
    public Integer getCodigoBancario() {
        return codigoBancario;
    }

    /**
     * Define o código bancário.
     * @param codigoBancario código identificador do banco
     */
    public void setCodigoBancario(Integer codigoBancario) {
        this.codigoBancario = codigoBancario;
    }
}
