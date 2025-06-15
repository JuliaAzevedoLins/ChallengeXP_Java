package com.challenge.investimentos.investimentos_api.model;

import com.challenge.investimentos.investimentos_api.enums.TipoInvestimentoEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

/**
 * Entidade que representa um investimento realizado por um usuário.
 * Contém informações sobre o banco, tipo de investimento, valores iniciais,
 * rentabilidade e a relação com o usuário investidor.
 */
@Entity
@Table(name = "INVESTIMENTO")
public class Investimento {

    /** Identificador único do investimento. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Usuário investidor associado ao investimento. */
    @ManyToOne
    @JoinColumn(name = "USUARIO_INVESTIMENTO_ID", nullable = false)
    @JsonBackReference
    private UsuarioInvestimento usuarioInvestimento;

    /** Nome do banco onde o investimento está registrado. */
    @Column(name = "NOME_BANCO")
    private String nomeBanco;

    /** Código bancário do banco. */
    @Column(name = "CODIGO_BANCARIO")
    private Integer codigoBancario;

    /** Tipo do investimento (ex: renda fixa, ações, etc). */
    @Column(name = "TIPO_INVESTIMENTO")
    @Enumerated(EnumType.STRING)
    private TipoInvestimentoEnum tipoInvestimento;


    /** Nome do investimento. */
    @Column(name = "NOME_INVESTIMENTO")
    private String nomeInvestimento;

    /** Valor inicial investido. */
    @Column(name = "MONTANTE_INICIAL")
    private Double montanteInicial;

    /** Valor inicial da ação, se aplicável. */
    @Column(name = "VALOR_INICIAL_ACAO")
    private Double valorInicialAcao;

    /** Taxa de rentabilidade do investimento. */
    @Column(name = "TAXA_RENTABILIDADE")
    private String taxaRentabilidade;

    /** Número inicial de ações, se aplicável. */
    @Column(name = "NUMERO_ACOES_INICIAL")
    private Integer numeroAcoesInicial;

    /** Lista de rentabilidades diárias associadas ao investimento. */
    @OneToMany(mappedBy = "investimento", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<RentabilidadeDiaria> rentabilidadeDiaria;

    /**
     * Obtém o identificador do investimento.
     * @return id do investimento
     */
    public Long getId() {
        return id;
    }

    /**
     * Obtém o usuário investidor associado.
     * @return usuário investidor
     */
    public UsuarioInvestimento getUsuarioInvestimento() {
        return usuarioInvestimento;
    }

    /**
     * Define o usuário investidor associado.
     * @param usuarioInvestimento usuário investidor
     */
    public void setUsuarioInvestimento(UsuarioInvestimento usuarioInvestimento) {
        this.usuarioInvestimento = usuarioInvestimento;
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
    public List<RentabilidadeDiaria> getRentabilidadeDiaria() {
        return rentabilidadeDiaria;
    }

    /**
     * Define a lista de rentabilidades diárias.
     * @param rentabilidadeDiaria lista de rentabilidades diárias
     */
    public void setRentabilidadeDiaria(List<RentabilidadeDiaria> rentabilidadeDiaria) {
        this.rentabilidadeDiaria = rentabilidadeDiaria;
    }
}