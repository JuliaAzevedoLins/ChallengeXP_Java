package com.challenge.investimentos.investimentos_api.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

/**
 * Entidade que representa um usuário investidor.
 * Contém o CPF de identificação e a lista de investimentos associados ao usuário.
 */
@Entity
@Table(name = "USUARIO_INVESTIMENTO")
public class UsuarioInvestimento {

    /**
     * Identificador único do usuário investidor.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * CPF de identificação do usuário investidor.
     */
    @Column(name = "CPF_IDENTIFICACAO", nullable = false, unique = true)
    private String cpfIdentificacao;

    /**
     * Lista de investimentos associados ao usuário investidor.
     */
    @OneToMany(mappedBy = "usuarioInvestimento", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Investimento> investimentos;

    /**
     * Obtém o identificador do usuário investidor.
     * @return id do usuário investidor
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o identificador do usuário investidor.
     * @param id identificador do usuário investidor
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtém o CPF de identificação do usuário investidor.
     * @return CPF de identificação
     */
    public String getCpfIdentificacao() {
        return cpfIdentificacao;
    }

    /**
     * Define o CPF de identificação do usuário investidor.
     * @param cpfIdentificacao CPF de identificação
     */
    public void setCpfIdentificacao(String cpfIdentificacao) {
        this.cpfIdentificacao = cpfIdentificacao;
    }

    /**
     * Obtém a lista de investimentos do usuário investidor.
     * @return lista de investimentos
     */
    public List<Investimento> getInvestimentos() {
        return investimentos;
    }

    /**
     * Define a lista de investimentos do usuário investidor.
     * @param investimentos lista de investimentos
     */
    public void setInvestimentos(List<Investimento> investimentos) {
        this.investimentos = investimentos;
    }
}