package com.challenge.investimentos.investimentos_api.repository;

import com.challenge.investimentos.investimentos_api.model.UsuarioInvestimento;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório para operações de persistência da entidade UsuarioInvestimento.
 */
public interface UsuarioInvestimentoRepository extends JpaRepository<UsuarioInvestimento, Long> {

    /**
     * Busca um usuário investidor pelo CPF de identificação.
     * @param cpfIdentificacao CPF de identificação do usuário
     * @return usuário investidor correspondente ou null se não encontrado
     */
    UsuarioInvestimento findByCpfIdentificacao(String cpfIdentificacao);
}