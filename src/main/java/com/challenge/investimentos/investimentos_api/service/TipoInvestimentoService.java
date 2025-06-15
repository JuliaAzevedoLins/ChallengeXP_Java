package com.challenge.investimentos.investimentos_api.service;

import com.challenge.investimentos.investimentos_api.dto.TipoInvestimentoDTO;
import com.challenge.investimentos.investimentos_api.repository.InvestimentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoInvestimentoService {

    private final InvestimentoRepository investimentoRepository;

    public TipoInvestimentoService(InvestimentoRepository investimentoRepository) {
        this.investimentoRepository = investimentoRepository;
    }

    public List<TipoInvestimentoDTO> listarTiposPorCpf(String cpf) {
    return investimentoRepository.findDistinctByUsuarioInvestimento_CpfIdentificacao(cpf).stream()
        .map(i -> new TipoInvestimentoDTO(i.getTipoInvestimento().name()))
        .distinct()
        .collect(Collectors.toList());
}

}
