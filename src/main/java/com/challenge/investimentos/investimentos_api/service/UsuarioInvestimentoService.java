package com.challenge.investimentos.investimentos_api.service;

import com.challenge.investimentos.investimentos_api.dto.InvestimentoDTO;
import com.challenge.investimentos.investimentos_api.dto.RentabilidadeDiariaDTO;
import com.challenge.investimentos.investimentos_api.dto.UsuarioInvestimentoDTO;

import com.challenge.investimentos.investimentos_api.enums.TipoInvestimentoEnum;
import com.challenge.investimentos.investimentos_api.model.Investimento;
import com.challenge.investimentos.investimentos_api.model.RentabilidadeDiaria;
import com.challenge.investimentos.investimentos_api.model.UsuarioInvestimento;
import com.challenge.investimentos.investimentos_api.repository.UsuarioInvestimentoRepository;
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
public class UsuarioInvestimentoService {

    @Autowired
    private UsuarioInvestimentoRepository usuarioInvestimentoRepository;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Transactional
    public ResponseEntity<String> criarUsuarioInvestimento(String cpfIdentificacao) {
        if (cpfIdentificacao == null || cpfIdentificacao.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("CPF do usuário é obrigatório.");
        }

        if (usuarioInvestimentoRepository.findByCpfIdentificacao(cpfIdentificacao) != null) {
            return ResponseEntity.badRequest().body("Usuário com esse CPF já existe.");
        }

        UsuarioInvestimento novoUsuario = new UsuarioInvestimento();
        novoUsuario.setCpfIdentificacao(cpfIdentificacao);
        novoUsuario.setInvestimentos(new ArrayList<>());

        usuarioInvestimentoRepository.save(novoUsuario);
        return ResponseEntity.ok("Usuário criado com sucesso.");
    }

    @Transactional
    public ResponseEntity<String> salvarInvestimentos(UsuarioInvestimentoDTO dto) {
        String cpf = dto.getCpfIdentificacao();
        if (cpf == null || cpf.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("CPF do usuário é obrigatório.");
        }

        UsuarioInvestimento usuario = usuarioInvestimentoRepository.findByCpfIdentificacao(cpf);
        if (usuario == null) {
            return ResponseEntity.badRequest().body("Usuário com CPF " + cpf + " não encontrado.");
        }

        List<InvestimentoDTO> investimentosDTO = dto.getDataUsuarioInvestimentos();
        if (investimentosDTO == null || investimentosDTO.isEmpty()) {
            return ResponseEntity.badRequest().body("Lista de investimentos não pode ser vazia.");
        }

        if (usuario.getInvestimentos() == null) {
            usuario.setInvestimentos(new ArrayList<>());
        } else {
            usuario.getInvestimentos().clear();
        }

        List<Investimento> investimentos = investimentosDTO.stream().map(investDTO -> {
            Investimento investimento = new Investimento();
            investimento.setUsuarioInvestimento(usuario);

            // Banco - usar a lógica do enum
            // Como o campo no modelo Investimento agora é String, defina-o diretamente.
            // O código bancário pode ser obtido e ignorado, se não for persistido.
            investimento.setNomeBanco(investDTO.getNomeBanco());

            // TipoInvestimentoEnum
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
            List<RentabilidadeDiariaDTO> rentabilidadeDTOs = investDTO.getRentabilidadeDiaria();
            if (rentabilidadeDTOs != null && !rentabilidadeDTOs.isEmpty()) {
                List<RentabilidadeDiaria> rentabilidades = rentabilidadeDTOs.stream().map(rdDTO -> {
                    LocalDate data;
                    try {
                        data = LocalDate.parse(rdDTO.getDataRentabilidadeDiaria(), FORMATTER);
                    } catch (Exception e) {
                        data = null;
                    }

                    // Mapeia do DTO para a classe de modelo
                    RentabilidadeDiaria rd = new RentabilidadeDiaria();
                    rd.setDataRentabilidadeDiaria(data);
                    rd.setValorDiarioAcao(rdDTO.getValorDiarioAcao());
                    rd.setTaxaDiarioRentabilidade(rdDTO.getTaxaDiarioRentabilidade());
                    rd.setMontanteAcumuladoDiario(rdDTO.getMontanteAcumuladoDiario());
                    return rd;
                }).collect(Collectors.toList());

                investimento.setRentabilidadeDiaria(rentabilidades);
            } else {
                investimento.setRentabilidadeDiaria(new ArrayList<>());
            }

            return investimento;
        }).collect(Collectors.toList());

        usuario.getInvestimentos().addAll(investimentos);
        usuarioInvestimentoRepository.save(usuario);

        return ResponseEntity.ok("Investimentos salvos com sucesso.");
    }

    @Transactional(readOnly = true)
    public ResponseEntity<List<UsuarioInvestimento>> listarTodosUsuarios() {
        return ResponseEntity.ok(usuarioInvestimentoRepository.findAll());
    }

    @Transactional(readOnly = true)
    public ResponseEntity<?> buscarPorCpf(String cpf) {
        UsuarioInvestimento usuario = usuarioInvestimentoRepository.findByCpfIdentificacao(cpf);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }

    @Transactional
    public ResponseEntity<String> deletarPorCpf(String cpf) {
        UsuarioInvestimento usuario = usuarioInvestimentoRepository.findByCpfIdentificacao(cpf);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        // Limpar investimentos antes de deletar
        if (usuario.getInvestimentos() != null) {
            usuario.getInvestimentos().clear();
        }

        usuarioInvestimentoRepository.delete(usuario);
        return ResponseEntity.ok("Usuário e seus investimentos foram deletados.");
    }
}