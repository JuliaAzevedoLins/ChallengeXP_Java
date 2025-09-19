package com.challenge.investimentos.investimentos_api.service;

import com.challenge.investimentos.investimentos_api.model.Banco;
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
     * @return lista de Value Objects Banco associados ao CPF informado
     */
    @Transactional(readOnly = true)
    public List<Banco> listarBancosPorCpf(String cpf) {
        // Busca todos os investimentos e depois extrai os bancos distintos
        return investimentoRepository.findByUsuarioInvestimento_CpfIdentificacao(cpf).stream()
                .map(investimento -> investimento.getBanco())
                .distinct()
                .collect(Collectors.toList());
    }
}