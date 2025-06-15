package com.challenge.investimentos.investimentos_api.dto;

import java.util.List;

import com.challenge.investimentos.investimentos_api.enums.TipoInvestimentoEnum;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

/**
 * DTO que representa os dados de um investimento, incluindo informações do banco,
 * tipo de investimento, valores iniciais e rentabilidade diária.
 */
public class InvestimentoDTO {

    /** Identificador único do investimento. */
    private Long id;

    /** Nome do banco onde o investimento está registrado. */
    @NotNull(message = "Nome do banco é obrigatório")
    @Size(min = 2, message = "Nome do banco deve ter pelo menos 2 caracteres")
    private String nomeBanco;

    /** Código bancário do banco. */
    @NotNull(message = "Código bancário é obrigatório")
    private Integer codigoBancario;

    /** Tipo do investimento (ex: renda fixa, ações, etc). */
    @NotNull(message = "Tipo de investimento é obrigatório")
    private TipoInvestimentoEnum tipoInvestimento;

    /** Nome do investimento. */
    @NotNull(message = "Nome do investimento é obrigatório")
    @Size(min = 2, message = "Nome do investimento deve ter pelo menos 2 caracteres")
    private String nomeInvestimento;

    /** Valor inicial investido. */
    @NotNull(message = "Montante inicial é obrigatório")
    @PositiveOrZero(message = "Montante inicial não pode ser negativo")
    private Double montanteInicial;

    /** Valor inicial da ação, se aplicável. */
    @PositiveOrZero(message = "Valor inicial da ação não pode ser negativo")
    private Double valorInicialAcao;

    /** Taxa de rentabilidade do investimento. */
    @NotNull(message = "Taxa de rentabilidade é obrigatória")
    private String taxaRentabilidade;

    /** Número inicial de ações, se aplicável. */
    @PositiveOrZero(message = "Número inicial de ações não pode ser negativo")
    private Integer numeroAcoesInicial;

    /** Lista de rentabilidades diárias associadas ao investimento. */
    @NotNull(message = "A lista de rentabilidades diárias não pode ser nula")
    @NotEmpty(message = "A lista de rentabilidades diárias não pode ser vazia")
    private List<RentabilidadeDiariaDTO> rentabilidadeDiaria;

    /**
     * Obtém o identificador do investimento.
     * @return id do investimento
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o identificador do investimento.
     * @param id id do investimento
     */
    public void setId(Long id) {
        this.id = id;
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
     * Obtém o código bancário.
     * @return código bancário
     */
    public Integer getCodigoBancario() {
        return codigoBancario;
    }

    /**
     * Define o código bancário.
     * @param codigoBancario código bancário
     */
    public void setCodigoBancario(Integer codigoBancario) {
        this.codigoBancario = codigoBancario;
    }

        /**
     * Obtém o tipo do investimento.
     * @return tipo do investimento
     */
    public TipoInvestimentoEnum getTipoInvestimento() {
        return tipoInvestimento;
    }

    /**
     * Define o tipo do investimento.
     * @param tipoInvestimento tipo do investimento
     */
    public void setTipoInvestimento(TipoInvestimentoEnum tipoInvestimento) {
        this.tipoInvestimento = tipoInvestimento;
    }

    /**
     * Obtém o nome do investimento.
     * @return nome do investimento
     */
    public String getNomeInvestimento() {
        return nomeInvestimento;
    }

    /**
     * Define o nome do investimento.
     * @param nomeInvestimento nome do investimento
     */
    public void setNomeInvestimento(String nomeInvestimento) {
        this.nomeInvestimento = nomeInvestimento;
    }

    /**
     * Obtém o montante inicial investido.
     * @return montante inicial
     */
    public Double getMontanteInicial() {
        return montanteInicial;
    }

    /**
     * Define o montante inicial investido.
     * @param montanteInicial montante inicial
     */
    public void setMontanteInicial(Double montanteInicial) {
        this.montanteInicial = montanteInicial;
    }

    /**
     * Obtém o valor inicial da ação.
     * @return valor inicial da ação
     */
    public Double getValorInicialAcao() {
        return valorInicialAcao;
    }

    /**
     * Define o valor inicial da ação.
     * @param valorInicialAcao valor inicial da ação
     */
    public void setValorInicialAcao(Double valorInicialAcao) {
        this.valorInicialAcao = valorInicialAcao;
    }

    /**
     * Obtém a taxa de rentabilidade.
     * @return taxa de rentabilidade
     */
    public String getTaxaRentabilidade() {
        return taxaRentabilidade;
    }

    /**
     * Define a taxa de rentabilidade.
     * @param taxaRentabilidade taxa de rentabilidade
     */
    public void setTaxaRentabilidade(String taxaRentabilidade) {
        this.taxaRentabilidade = taxaRentabilidade;
    }

    /**
     * Obtém o número inicial de ações.
     * @return número inicial de ações
     */
    public Integer getNumeroAcoesInicial() {
        return numeroAcoesInicial;
    }

    /**
     * Define o número inicial de ações.
     * @param numeroAcoesInicial número inicial de ações
     */
    public void setNumeroAcoesInicial(Integer numeroAcoesInicial) {
        this.numeroAcoesInicial = numeroAcoesInicial;
    }

    /**
     * Obtém a lista de rentabilidades diárias.
     * @return lista de rentabilidades diárias
     */
    public List<RentabilidadeDiariaDTO> getRentabilidadeDiaria() {
        return rentabilidadeDiaria;
    }

    /**
     * Define a lista de rentabilidades diárias.
     * @param rentabilidadeDiaria lista de rentabilidades diárias
     */
    public void setRentabilidadeDiaria(List<RentabilidadeDiariaDTO> rentabilidadeDiaria) {
        this.rentabilidadeDiaria = rentabilidadeDiaria;
    }
}