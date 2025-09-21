package com.challenge.investimentos.investimentos_api.service;


import com.challenge.investimentos.investimentos_api.repository.InvestimentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Serviço responsável pelas operações relacionadas a bancos associados aos investimentos dos usuários.
 */
@Service
public class BancoService {

    private final InvestimentoRepository investimentoRepository;

    /**
     * Construtor para injeção do repositório de investimentos.
     * @param investimentoRepository repositório de investimentos
     */
    public BancoService(InvestimentoRepository investimentoRepository) {
        this.investimentoRepository = investimentoRepository;
    }

    /**
     * Lista os bancos distintos associados a um usuário investidor pelo CPF.
     *
     * @param cpf CPF do usuário investidor
     * @return lista de nomes de bancos (String) associados ao CPF informado
     */
    @Transactional(readOnly = true)
    public List<String> listarBancosPorCpf(String cpf) {
        // Busca todos os investimentos e depois extrai os nomes dos bancos distintos
        return investimentoRepository.findByUsuarioInvestimento_Cpf_Cpf(cpf).stream()
                .map(investimento -> investimento.getNomeBanco()) // <-- Linha corrigida
                .distinct()
                .collect(Collectors.toList());
    }
}