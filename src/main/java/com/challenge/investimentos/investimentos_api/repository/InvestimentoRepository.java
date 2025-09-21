package com.challenge.investimentos.investimentos_api.repository;

import com.challenge.investimentos.investimentos_api.model.Investimento;
import org.springframework.data.jpa.repository.JpaRepository;
import com.challenge.investimentos.investimentos_api.model.UsuarioInvestimento;

import java.util.List;

/**
 * Repositório JPA para a entidade Investimento.
 * Fornece métodos para operações de persistência e consultas customizadas de investimentos.
 */
public interface InvestimentoRepository extends JpaRepository<Investimento, Long> {

    /**
     * Busca todos os investimentos associados a um usuário investidor.
     *
     * @param usuarioInvestimento usuário investidor
     * @return lista de investimentos do usuário
     */
    List<Investimento> findByUsuarioInvestimento(UsuarioInvestimento usuarioInvestimento);

    /**
     * Busca todos os investimentos associados a um CPF de usuário investidor.
     *
     * @param cpf CPF do usuário investidor
     * @return lista de investimentos do usuário
     */
    List<Investimento> findByUsuarioInvestimento_Cpf_Cpf(String cpf);
}