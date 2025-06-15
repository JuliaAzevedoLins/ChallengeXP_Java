package com.challenge.investimentos.investimentos_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO que representa os dados de um banco associado a um investimento.
 */
public class BancoDTO {

    /** Nome do banco. */
    @Schema(example = "Nubank")
    private String nomeBanco;

    /** Código bancário do banco. */
    @Schema(example = "260")
    private Integer codigoBancario;

    /**
     * Construtor padrão.
     */
    public BancoDTO() {}

    /**
     * Construtor com parâmetros.
     * @param nomeBanco nome do banco
     * @param codigoBancario código bancário do banco
     */
    public BancoDTO(String nomeBanco, Integer codigoBancario) {
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
     * Obtém o código bancário do banco.
     * @return código bancário
     */
    public Integer getCodigoBancario() {
        return codigoBancario;
    }

    /**
     * Define o código bancário do banco.
     * @param codigoBancario código bancário
     */
    public void setCodigoBancario(Integer codigoBancario) {
        this.codigoBancario = codigoBancario;
    }
}