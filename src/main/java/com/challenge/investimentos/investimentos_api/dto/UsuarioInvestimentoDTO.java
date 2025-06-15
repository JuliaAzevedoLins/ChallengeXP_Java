package com.challenge.investimentos.investimentos_api.dto;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO que representa os dados de um usuário investidor,
 * incluindo o CPF de identificação e a lista de investimentos do usuário.
 */
public class UsuarioInvestimentoDTO {

    /**
     * CPF de identificação do usuário investidor.
     */
    @Schema(example = "12345678900")
    private String cpfIdentificacao;

    /**
     * Lista de investimentos associados ao usuário.
     */
    @Schema(
        description = "Lista de investimentos do usuário",
        example = "[{\"id\":1,\"nomeBanco\":\"Nubank\",\"codigoBancario\":260,\"tipoInvestimento\":\"RENDA_FIXA\",\"nomeInvestimento\":\"CDB Nubank\",\"montanteInicial\":1000.0,\"valorInicialAcao\":0.0,\"taxaRentabilidade\":\"0.12\",\"numeroAcoesInicial\":0,\"rentabilidadeDiaria\":[{\"dataRentabilidadeDiaria\":\"01-01-2025\",\"valorDiarioAcao\":100.5,\"taxaDiarioRentabilidade\":\"0.12\",\"montanteAcumuladoDiario\":1050.75}]}]"
    )
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