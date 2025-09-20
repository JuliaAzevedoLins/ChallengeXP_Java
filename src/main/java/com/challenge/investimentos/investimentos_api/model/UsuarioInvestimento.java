package com.challenge.investimentos.investimentos_api.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USUARIO_INVESTIMENTO")
public class UsuarioInvestimento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CPF_IDENTIFICACAO", nullable = false, unique = true)
    private String cpfIdentificacao;

    @OneToMany(mappedBy = "usuarioInvestimento", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Investimento> investimentos = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpfIdentificacao() {
        return cpfIdentificacao;
    }

    public void setCpfIdentificacao(String cpfIdentificacao) {
        this.cpfIdentificacao = cpfIdentificacao;
    }

    public List<Investimento> getInvestimentos() {
        return investimentos;
    }

    public void setInvestimentos(List<Investimento> investimentos) {
        this.investimentos = investimentos;
    }
}