package com.challenge.investimentos.investimentos_api.service;

import com.challenge.investimentos.investimentos_api.dto.InvestimentoDTO;
import com.challenge.investimentos.investimentos_api.dto.UsuarioInvestimentoDTO;

import com.challenge.investimentos.investimentos_api.model.Investimento;
import com.challenge.investimentos.investimentos_api.model.RentabilidadeDiaria;
import com.challenge.investimentos.investimentos_api.model.UsuarioInvestimento;
import com.challenge.investimentos.investimentos_api.enums.TipoInvestimentoEnum;
import com.challenge.investimentos.investimentos_api.repository.InvestimentoRepository;
import com.challenge.investimentos.investimentos_api.repository.UsuarioInvestimentoRepository;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvestimentoService {

    private final UsuarioInvestimentoRepository usuarioInvestimentoRepository;
    private final InvestimentoRepository investimentoRepository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Autowired
    public InvestimentoService(UsuarioInvestimentoRepository usuarioInvestimentoRepository,
                               InvestimentoRepository investimentoRepository) {
        this.usuarioInvestimentoRepository = usuarioInvestimentoRepository;
        this.investimentoRepository = investimentoRepository;
    }

    @Transactional
    public ResponseEntity<String> salvarInvestimentos(UsuarioInvestimentoDTO dto) {
        if (dto.getCpfIdentificacao() == null || dto.getCpfIdentificacao().isEmpty()) {
            return ResponseEntity.badRequest().body("CPF do usuário é obrigatório");
        }

        UsuarioInvestimento usuario = usuarioInvestimentoRepository.findByCpfIdentificacao(dto.getCpfIdentificacao());
        if (usuario == null) {
            return ResponseEntity.badRequest().body("Usuário com CPF " + dto.getCpfIdentificacao() + " não encontrado");
        }

        if (dto.getDataUsuarioInvestimentos() == null || dto.getDataUsuarioInvestimentos().isEmpty()) {
            return ResponseEntity.badRequest().body("Lista de investimentos não pode ser vazia");
        }

        List<Investimento> investimentos = dto.getDataUsuarioInvestimentos().stream().map(investDTO -> {
            Investimento investimento = new Investimento();
            investimento.setUsuarioInvestimento(usuario);

            // Banco - usar a lógica do enum
            // Você só precisa do nome do banco, que está no DTO
            investimento.setNomeBanco(investDTO.getNomeBanco());

            // Tipo de investimento
            TipoInvestimentoEnum tipoInvestimento;
            try {
                tipoInvestimento = TipoInvestimentoEnum.valueOf(investDTO.getTipoInvestimento().toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Tipo de investimento inválido: " + investDTO.getTipoInvestimento());
            }
            investimento.setTipoInvestimento(tipoInvestimento);
            investimento.setNomeInvestimento(investDTO.getNomeInvestimento());

            // Valores numéricos
            investimento.setMontanteInicial(investDTO.getMontanteInicial());
            investimento.setValorInicialAcao(investDTO.getValorInicialAcao());
            investimento.setTaxaRentabilidade(investDTO.getTaxaRentabilidade());
            investimento.setNumeroAcoesInicial(investDTO.getNumeroAcoesInicial());

            // Rentabilidade diária
            if (investDTO.getRentabilidadeDiaria() != null && !investDTO.getRentabilidadeDiaria().isEmpty()) {
                List<RentabilidadeDiaria> rentabilidades = investDTO.getRentabilidadeDiaria().stream()
                        .map(rdDTO -> {
                            RentabilidadeDiaria rd = new RentabilidadeDiaria();
                            rd.setDataRentabilidadeDiaria(LocalDate.parse(rdDTO.getDataRentabilidadeDiaria(), formatter));
                            rd.setValorDiarioAcao(rdDTO.getValorDiarioAcao());
                            rd.setTaxaDiarioRentabilidade(rdDTO.getTaxaDiarioRentabilidade());
                            rd.setMontanteAcumuladoDiario(rdDTO.getMontanteAcumuladoDiario());
                            return rd;
                        }).collect(Collectors.toList());
                investimento.setRentabilidadeDiaria(rentabilidades);
            }

            return investimento;
        }).collect(Collectors.toList());

        investimentoRepository.saveAll(investimentos);
        return ResponseEntity.ok("Investimentos atualizados com sucesso");
    }

    @Transactional(readOnly = true)
    public ResponseEntity<List<Investimento>> listarTodos() {
        return ResponseEntity.ok(investimentoRepository.findAll());
    }

    @Transactional(readOnly = true)
    public ResponseEntity<List<Investimento>> listarPorCpf(String cpf) {
        UsuarioInvestimento usuario = usuarioInvestimentoRepository.findByCpfIdentificacao(cpf);
        if (usuario == null) return ResponseEntity.notFound().build();

        List<Investimento> investimentos = investimentoRepository.findByUsuarioInvestimento(usuario);
        return ResponseEntity.ok(investimentos);
    }

    @Transactional
    public ResponseEntity<String> deletarPorId(Long id) {
        if (!investimentoRepository.existsById(id)) return ResponseEntity.notFound().build();

        investimentoRepository.deleteById(id);
        return ResponseEntity.ok("Investimento deletado com sucesso");
    }

    @Transactional
    public ResponseEntity<String> atualizarInvestimento(Long id, InvestimentoDTO dto) {
        Investimento investimentoExistente = investimentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Investimento não encontrado"));

        TipoInvestimentoEnum tipoInvestimento;
        try {
            tipoInvestimento = TipoInvestimentoEnum.valueOf(dto.getTipoInvestimento().toUpperCase());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Tipo de investimento inválido: " + dto.getTipoInvestimento());
        }

        investimentoExistente.setTipoInvestimento(tipoInvestimento);
        investimentoExistente.setNomeInvestimento(dto.getNomeInvestimento());
        investimentoExistente.setMontanteInicial(dto.getMontanteInicial());
        investimentoExistente.setValorInicialAcao(dto.getValorInicialAcao());
        investimentoExistente.setTaxaRentabilidade(dto.getTaxaRentabilidade());
        investimentoExistente.setNumeroAcoesInicial(dto.getNumeroAcoesInicial());
        
        // Ajuste para definir o nome do banco
        investimentoExistente.setNomeBanco(dto.getNomeBanco());

        // Atualiza rentabilidade diária
        if (investimentoExistente.getRentabilidadeDiaria() == null) {
            investimentoExistente.setRentabilidadeDiaria(new ArrayList<>());
        }
        List<RentabilidadeDiaria> listaExistente = investimentoExistente.getRentabilidadeDiaria();
        listaExistente.clear();

        if (dto.getRentabilidadeDiaria() != null) {
            dto.getRentabilidadeDiaria().forEach(rdDTO -> {
                RentabilidadeDiaria rd = new RentabilidadeDiaria();
                rd.setDataRentabilidadeDiaria(LocalDate.parse(rdDTO.getDataRentabilidadeDiaria(), formatter));
                rd.setValorDiarioAcao(rdDTO.getValorDiarioAcao());
                rd.setTaxaDiarioRentabilidade(rdDTO.getTaxaDiarioRentabilidade());
                rd.setMontanteAcumuladoDiario(rdDTO.getMontanteAcumuladoDiario());
                listaExistente.add(rd);
            });
        }

        investimentoRepository.save(investimentoExistente);
        return ResponseEntity.ok("Investimento atualizado com sucesso");
    }

    @Transactional
    public ResponseEntity<String> criarInvestimento(@Valid UsuarioInvestimentoDTO dto) {
        return salvarInvestimentos(dto);
    }
}