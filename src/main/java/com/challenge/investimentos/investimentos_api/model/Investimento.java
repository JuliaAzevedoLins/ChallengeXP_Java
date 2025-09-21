package com.challenge.investimentos.investimentos_api.model;

import com.challenge.investimentos.investimentos_api.enums.TipoInvestimentoEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidade que representa um investimento do usuário.
 * 
 * Contém dados básicos (nome, montante, taxa, etc.), o tipo de investimento,
 * e os relacionamentos com o usuário investidor e a lista de rentabilidades diárias.
 */
@Entity
@Table(name = "INVESTIMENTO")
public class Investimento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "investimento_seq")
    @SequenceGenerator(name = "investimento_seq", sequenceName = "INVESTIMENTO_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "NOME_BANCO")
    private String nomeBanco;

    @Column(name = "NOME_INVESTIMENTO", nullable = false)
    private String nomeInvestimento;

    @Column(name = "MONTANTE_INICIAL", precision = 15, scale = 2)
    private BigDecimal montanteInicial;

    @Column(name = "VALOR_INICIAL_ACAO", precision = 15, scale = 2)
    private BigDecimal valorInicialAcao;

    @Column(name = "TAXA_RENTABILIDADE", precision = 10, scale = 4)
    private BigDecimal taxaRentabilidade;

    @Column(name = "NUMERO_ACOES_INICIAL")
    private Integer numeroAcoesInicial;

    /** Tipo do investimento, persistido como texto. */
    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_INVESTIMENTO")
    private TipoInvestimentoEnum tipoInvestimento;

    /**
     * Associação N:1 com o usuário investidor.
     * Marcado com {@link JsonBackReference} para evitar recursão na serialização JSON.
     */
    @ManyToOne
    @JoinColumn(name = "USUARIO_INVESTIMENTO_ID")
    @JsonBackReference
    private UsuarioInvestimento usuarioInvestimento;

    /** Rentabilidades diárias associadas ao investimento (cascade + orphanRemoval). */
    @OneToMany(mappedBy = "investimento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RentabilidadeDiaria> rentabilidadeDiaria = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeBanco() {
        return nomeBanco;
    }

    public void setNomeBanco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
    }

    public String getNomeInvestimento() {
        return nomeInvestimento;
    }

    public void setNomeInvestimento(String nomeInvestimento) {
        this.nomeInvestimento = nomeInvestimento;
    }

    public BigDecimal getMontanteInicial() {
        return montanteInicial;
    }

    public void setMontanteInicial(BigDecimal montanteInicial) {
        this.montanteInicial = montanteInicial;
    }

    public BigDecimal getValorInicialAcao() {
        return valorInicialAcao;
    }

    public void setValorInicialAcao(BigDecimal valorInicialAcao) {
        this.valorInicialAcao = valorInicialAcao;
    }

    public BigDecimal getTaxaRentabilidade() {
        return taxaRentabilidade;
    }

    public void setTaxaRentabilidade(BigDecimal taxaRentabilidade) {
        this.taxaRentabilidade = taxaRentabilidade;
    }

    public Integer getNumeroAcoesInicial() {
        return numeroAcoesInicial;
    }

    public void setNumeroAcoesInicial(Integer numeroAcoesInicial) {
        this.numeroAcoesInicial = numeroAcoesInicial;
    }

    public TipoInvestimentoEnum getTipoInvestimento() {
        return tipoInvestimento;
    }

    public void setTipoInvestimento(TipoInvestimentoEnum tipoInvestimento) {
        this.tipoInvestimento = tipoInvestimento;
    }

    public UsuarioInvestimento getUsuarioInvestimento() {
        return usuarioInvestimento;
    }

    public void setUsuarioInvestimento(UsuarioInvestimento usuarioInvestimento) {
        this.usuarioInvestimento = usuarioInvestimento;
    }

    public List<RentabilidadeDiaria> getRentabilidadeDiaria() {
        return rentabilidadeDiaria;
    }

    public void setRentabilidadeDiaria(List<RentabilidadeDiaria> rentabilidadeDiaria) {
        this.rentabilidadeDiaria = rentabilidadeDiaria;
    }
}