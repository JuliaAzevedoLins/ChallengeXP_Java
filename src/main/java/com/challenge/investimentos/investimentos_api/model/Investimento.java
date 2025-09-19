package com.challenge.investimentos.investimentos_api.model;

import com.challenge.investimentos.investimentos_api.enums.TipoInvestimentoEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "INVESTIMENTO")
public class Investimento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USUARIO_INVESTIMENTO_ID", nullable = false)
    @JsonBackReference
    private UsuarioInvestimento usuarioInvestimento;

    // 🟢 Mapeamento correto do Value Object Banco
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "nomeBanco", column = @Column(name = "BANCO_NOME")),
        @AttributeOverride(name = "codigoBancario", column = @Column(name = "BANCO_CODIGO"))
    })
    private Banco banco;

    @Column(name = "TIPO_INVESTIMENTO")
    @Enumerated(EnumType.STRING)
    private TipoInvestimentoEnum tipoInvestimento;

    @Column(name = "NOME_INVESTIMENTO")
    private String nomeInvestimento;

    @Column(name = "MONTANTE_INICIAL")
    private Double montanteInicial;

    @Column(name = "VALOR_INICIAL_ACAO")
    private Double valorInicialAcao;

    @Column(name = "TAXA_RENTABILIDADE")
    private String taxaRentabilidade;

    @Column(name = "NUMERO_ACOES_INICIAL")
    private Integer numeroAcoesInicial;

    @ElementCollection
    @CollectionTable(name = "RENTABILIDADE_DIARIA_TABLE", joinColumns = @JoinColumn(name = "INVESTIMENTO_ID"))
    private List<RentabilidadeDiaria> rentabilidadeDiaria;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public UsuarioInvestimento getUsuarioInvestimento() { return usuarioInvestimento; }
    public void setUsuarioInvestimento(UsuarioInvestimento usuarioInvestimento) { this.usuarioInvestimento = usuarioInvestimento; }

    public Banco getBanco() { return banco; }
    public void setBanco(Banco banco) { this.banco = banco; }

    public TipoInvestimentoEnum getTipoInvestimento() { return tipoInvestimento; }
    public void setTipoInvestimento(TipoInvestimentoEnum tipoInvestimento) { this.tipoInvestimento = tipoInvestimento; }

    public String getNomeInvestimento() { return nomeInvestimento; }
    public void setNomeInvestimento(String nomeInvestimento) { this.nomeInvestimento = nomeInvestimento; }

    public Double getMontanteInicial() { return montanteInicial; }
    public void setMontanteInicial(Double montanteInicial) { this.montanteInicial = montanteInicial; }

    public Double getValorInicialAcao() { return valorInicialAcao; }
    public void setValorInicialAcao(Double valorInicialAcao) { this.valorInicialAcao = valorInicialAcao; }

    public String getTaxaRentabilidade() { return taxaRentabilidade; }
    public void setTaxaRentabilidade(String taxaRentabilidade) { this.taxaRentabilidade = taxaRentabilidade; }

    public Integer getNumeroAcoesInicial() { return numeroAcoesInicial; }
    public void setNumeroAcoesInicial(Integer numeroAcoesInicial) { this.numeroAcoesInicial = numeroAcoesInicial; }

    public List<RentabilidadeDiaria> getRentabilidadeDiaria() { return rentabilidadeDiaria; }
    public void setRentabilidadeDiaria(List<RentabilidadeDiaria> rentabilidadeDiaria) { this.rentabilidadeDiaria = rentabilidadeDiaria; }
}
