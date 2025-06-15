package com.challenge.investimentos.investimentos_api.repository;

import com.challenge.investimentos.investimentos_api.model.Investimento;
import org.springframework.data.jpa.repository.JpaRepository;
import com.challenge.investimentos.investimentos_api.model.UsuarioInvestimento;


import java.util.List;

public interface InvestimentoRepository extends JpaRepository<Investimento, Long> {
    List<Investimento> findByUsuarioInvestimento(UsuarioInvestimento usuarioInvestimento);
    List<Investimento> findDistinctByUsuarioInvestimento_CpfIdentificacao(String cpf); // Para o serviço de banco e tipo
}
