package com.challenge.investimentos.investimentos_api.dto;

import java.util.List;

/**
 * DTO que representa os dados de um usuário investidor,
 * incluindo o CPF de identificação e a lista de investimentos do usuário.
 */
public class UsuarioInvestimentoDTO {

    /** CPF de identificação do usuário investidor. */
    private String cpfIdentificacao;

    /** Lista de investimentos associados ao usuário. */
    private List<InvestimentoDTO> dataUsuarioInvestimentos;

    /**
     * Obtém o CPF de identificação do usuário.
     * @return CPF de identificação
     */
    public String getCpfIdentificacao() {
        return cpfIdentificacao;
    }

    /**
     * Define o CPF de identificação do usuário.
     * @param cpfIdentificacao CPF de identificação
     */
    public void setCpfIdentificacao(String cpfIdentificacao) {
        this.cpfIdentificacao = cpfIdentificacao;
    }

    /**
     * Obtém a lista de investimentos do usuário.
     * @return lista de investimentos
     */
    public List<InvestimentoDTO> getDataUsuarioInvestimentos() {
        return dataUsuarioInvestimentos;
    }

    /**
     * Define a lista de investimentos do usuário.
     * @param dataUsuarioInvestimentos lista de investimentos
     */
    public void setDataUsuarioInvestimentos(List<InvestimentoDTO> dataUsuarioInvestimentos) {
        this.dataUsuarioInvestimentos = dataUsuarioInvestimentos;
    }
}