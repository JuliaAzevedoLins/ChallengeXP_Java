package com.challenge.investimentos.investimentos_api.repository;

import com.challenge.investimentos.investimentos_api.model.UsuarioInvestimento;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório JPA para operações de persistência da entidade UsuarioInvestimento.
 * Fornece métodos para salvar, buscar, atualizar e remover usuários investidores.
 */
public interface UsuarioInvestimentoRepository extends JpaRepository<UsuarioInvestimento, Long> {

    /**
     * Busca um usuário investidor pelo CPF de identificação.
     *
     * @param cpf CPF de identificação do usuário
     * @return usuário investidor correspondente ou null se não encontrado
     */
    UsuarioInvestimento findByCpf_Cpf(String cpf);
}