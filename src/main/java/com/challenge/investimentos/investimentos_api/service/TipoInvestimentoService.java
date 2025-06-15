package com.challenge.investimentos.investimentos_api.service;

import com.challenge.investimentos.investimentos_api.dto.TipoInvestimentoDTO;
import com.challenge.investimentos.investimentos_api.repository.InvestimentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Serviço responsável pelas operações relacionadas aos tipos de investimento
 * associados aos investimentos dos usuários.
 */
@Service
public class TipoInvestimentoService {

    private final InvestimentoRepository investimentoRepository;

    /**
     * Construtor para injeção do repositório de investimentos.
     * @param investimentoRepository repositório de investimentos
     */
    public TipoInvestimentoService(InvestimentoRepository investimentoRepository) {
        this.investimentoRepository = investimentoRepository;
    }

    /**
     * Lista os tipos de investimento distintos associados a um usuário investidor pelo CPF.
     *
     * @param cpf CPF do usuário investidor
     * @return lista de tipos de investimento associados ao CPF informado
     */
    public List<TipoInvestimentoDTO> listarTiposPorCpf(String cpf) {
        return investimentoRepository.findDistinctByUsuarioInvestimento_CpfIdentificacao(cpf).stream()
            .map(i -> new TipoInvestimentoDTO(i.getTipoInvestimento().name()))
            .distinct()
            .collect(Collectors.toList());
    }
}