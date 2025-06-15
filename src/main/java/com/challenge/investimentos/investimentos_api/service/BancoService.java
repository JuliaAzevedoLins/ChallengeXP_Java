package com.challenge.investimentos.investimentos_api.service;

import com.challenge.investimentos.investimentos_api.dto.BancoDTO;
import com.challenge.investimentos.investimentos_api.repository.InvestimentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BancoService {

    private final InvestimentoRepository investimentoRepository;

    public BancoService(InvestimentoRepository investimentoRepository) {
        this.investimentoRepository = investimentoRepository;
    }

    public List<BancoDTO> listarBancosPorCpf(String cpf) {
        return investimentoRepository.findDistinctByUsuarioInvestimento_CpfIdentificacao(cpf).stream()
            .map(i -> new BancoDTO(i.getNomeBanco(), i.getCodigoBancario()))
            .distinct()
            .collect(Collectors.toList());
    }
}
