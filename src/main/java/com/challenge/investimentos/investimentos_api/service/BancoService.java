package com.challenge.investimentos.investimentos_api.service;

import com.challenge.investimentos.investimentos_api.dto.BancoDTO;
import com.challenge.investimentos.investimentos_api.repository.InvestimentoRepository;
import org.springframework.stereotype.Service;

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
     * @return lista de bancos (nome e código) associados ao CPF informado
     */
    public List<BancoDTO> listarBancosPorCpf(String cpf) {
        return investimentoRepository.findDistinctByUsuarioInvestimento_CpfIdentificacao(cpf).stream()
            .map(i -> new BancoDTO(i.getNomeBanco(), i.getCodigoBancario()))
            .distinct()
            .collect(Collectors.toList());
    }
}