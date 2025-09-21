package com.challenge.investimentos.investimentos_api.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidade que representa o usuário investidor no sistema.
 * 
 * Possui um CPF de identificação único e a lista de seus investimentos.
 */
@Entity
@Table(name = "USUARIO_INVESTIMENTO")
public class UsuarioInvestimento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** CPF do investidor como Value Object. */
    @Embedded
    @AttributeOverride(name = "cpf", column = @Column(name = "CPF_IDENTIFICACAO", nullable = false, unique = true, length = 11, updatable = false))
    private CpfVO cpf;

    /**
     * Relação 1:N com os investimentos do usuário.
     * A anotação {@link JsonManagedReference} complementa o {@code @JsonBackReference}
     * em {@link Investimento} para evitar recursão na serialização JSON.
     */
    @OneToMany(mappedBy = "usuarioInvestimento", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Investimento> investimentos = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter compatível para o CPF (exposição como String normalizada).
     */
    public String getCpfIdentificacao() {
        return cpf != null ? cpf.getCpf() : null;
    }

    /**
     * Setter compatível para o CPF a partir de String (cria o VO internamente).
     */
    public void setCpfIdentificacao(String cpfIdentificacao) {
        this.cpf = cpfIdentificacao != null ? new CpfVO(cpfIdentificacao) : null;
    }

    public List<Investimento> getInvestimentos() {
        return investimentos;
    }

    public void setInvestimentos(List<Investimento> investimentos) {
        this.investimentos = investimentos;
    }
}