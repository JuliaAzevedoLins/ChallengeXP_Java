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

/**
 * Serviço para gerenciar usuários investidores e seus investimentos.
 * 
 * Executa validações de entrada, mapeia DTOs para entidades e coordena a
 * persistência via repositório.
 */
@Service
public class UsuarioInvestimentoService {

    @Autowired
    private UsuarioInvestimentoRepository usuarioInvestimentoRepository;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    /**
     * Cria um novo usuário investidor com o CPF informado.
     * @param cpfIdentificacao CPF do usuário
     * @return 200 quando criado; 400 se CPF ausente ou já existente
     */
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

    /**
     * Substitui os investimentos do usuário informado pelos contidos no DTO.
     * 
     * Limpa a coleção atual (se existir) e popula novamente com base no DTO,
     * validando tipo de investimento e datas.
     *
     * @param dto dados do usuário e seus investimentos
     * @return 200 em caso de sucesso; 400 quando houver validação inválida
     */
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

            investimento.setNomeBanco(investDTO.getNomeBanco());

            TipoInvestimentoEnum tipoInvestimento;
            try {
                tipoInvestimento = TipoInvestimentoEnum.valueOf(investDTO.getTipoInvestimento().toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Tipo de investimento inválido: " + investDTO.getTipoInvestimento());
            }
            investimento.setTipoInvestimento(tipoInvestimento);
            investimento.setNomeInvestimento(investDTO.getNomeInvestimento());

            investimento.setMontanteInicial(investDTO.getMontanteInicial());
            investimento.setValorInicialAcao(investDTO.getValorInicialAcao());
            investimento.setTaxaRentabilidade(investDTO.getTaxaRentabilidade());
            investimento.setNumeroAcoesInicial(investDTO.getNumeroAcoesInicial());

            List<RentabilidadeDiariaDTO> rentabilidadeDTOs = investDTO.getRentabilidadeDiaria();
            if (rentabilidadeDTOs != null && !rentabilidadeDTOs.isEmpty()) {
                List<RentabilidadeDiaria> rentabilidades = rentabilidadeDTOs.stream().map(rdDTO -> {
                    LocalDate data;
                    try {
                        data = LocalDate.parse(rdDTO.getDataRentabilidadeDiaria(), FORMATTER);
                    } catch (Exception e) {
                        data = null;
                    }
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

    /**
     * Lista todos os usuários investidores.
     * @return lista completa de usuários
     */
    @Transactional(readOnly = true)
    public ResponseEntity<List<UsuarioInvestimento>> listarTodosUsuarios() {
        return ResponseEntity.ok(usuarioInvestimentoRepository.findAll());
    }

    /**
     * Busca um usuário pelo CPF.
     * @param cpf CPF do usuário
     * @return 200 com o usuário; 404 se não encontrado
     */
    @Transactional(readOnly = true)
    public ResponseEntity<?> buscarPorCpf(String cpf) {
        UsuarioInvestimento usuario = usuarioInvestimentoRepository.findByCpfIdentificacao(cpf);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }

    /**
     * Deleta um usuário e seus investimentos pelo CPF.
     * @param cpf CPF do usuário
     * @return 200 quando deletado; 404 se não encontrado
     */
    @Transactional
    public ResponseEntity<String> deletarPorCpf(String cpf) {
        UsuarioInvestimento usuario = usuarioInvestimentoRepository.findByCpfIdentificacao(cpf);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        if (usuario.getInvestimentos() != null) {
            usuario.getInvestimentos().clear();
        }

        usuarioInvestimentoRepository.delete(usuario);
        return ResponseEntity.ok("Usuário e seus investimentos foram deletados.");
    }
}