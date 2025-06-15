package com.challenge.investimentos.investimentos_api.dto;

public class BancoDTO {
    private String nomeBanco;
    private Integer codigoBancario;

    public BancoDTO() {}

    public BancoDTO(String nomeBanco, Integer codigoBancario) {
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