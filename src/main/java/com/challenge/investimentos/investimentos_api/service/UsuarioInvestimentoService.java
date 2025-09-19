package com.challenge.investimentos.investimentos_api.service;

import com.challenge.investimentos.investimentos_api.dto.InvestimentoDTO;
import com.challenge.investimentos.investimentos_api.dto.RentabilidadeDiariaDTO;
import com.challenge.investimentos.investimentos_api.dto.UsuarioInvestimentoDTO;
import com.challenge.investimentos.investimentos_api.model.Banco;
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
 * Serviço responsável pela lógica de negócios relacionada a usuários investidores.
 * Permite criar, buscar, listar, atualizar e deletar usuários investidores e seus investimentos.
 */
@Service
public class UsuarioInvestimentoService {

    @Autowired
    private UsuarioInvestimentoRepository usuarioInvestimentoRepository;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    /**
     * Salva ou atualiza os investimentos de um usuário investidor.
     * Sobrescreve os investimentos antigos do usuário.
     *
     * @param dto DTO contendo os dados do usuário e seus investimentos
     * @return ResponseEntity com mensagem de sucesso ou erro
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

        // Limpa a lista de investimentos antigos, se quiser sobrescrever
        if (usuario.getInvestimentos() == null) {
            usuario.setInvestimentos(new ArrayList<>());
        } else {
            usuario.getInvestimentos().clear();
        }

        List<Investimento> investimentos = investimentosDTO.stream().map(investDTO -> {
            Investimento investimento = new Investimento();
            investimento.setUsuarioInvestimento(usuario);
            
            // Cria e define o Value Object Banco
            investimento.setBanco(new Banco(investDTO.getNomeBanco(), investDTO.getCodigoBancario()));
            
            investimento.setTipoInvestimento(investDTO.getTipoInvestimento());
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
                    // Usa o construtor completo do Value Object
                    return new RentabilidadeDiaria(data, rdDTO.getValorDiarioAcao(), rdDTO.getTaxaDiarioRentabilidade(), rdDTO.getMontanteAcumuladoDiario());
                }).collect(Collectors.toList());

                investimento.setRentabilidadeDiaria(rentabilidades);
            }

            return investimento;
        }).collect(Collectors.toList());

        usuario.getInvestimentos().addAll(investimentos);
        usuarioInvestimentoRepository.save(usuario);

        return ResponseEntity.ok("Investimentos salvos com sucesso.");
    }

    /**
     * Lista todos os usuários investidores cadastrados.
     *
     * @return ResponseEntity com a lista de usuários investidores
     */
    @Transactional(readOnly = true)
    public ResponseEntity<List<UsuarioInvestimento>> listarTodosUsuarios() {
        return ResponseEntity.ok(usuarioInvestimentoRepository.findAll());
    }

    /**
     * Busca um usuário investidor pelo CPF.
     *
     * @param cpf CPF do usuário investidor
     * @return ResponseEntity com o usuário encontrado ou erro
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
     * Cria um novo usuário investidor.
     *
     * @param cpfIdentificacao CPF de identificação do novo usuário
     * @return ResponseEntity com mensagem de sucesso ou erro
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

        usuarioInvestimentoRepository.save(novoUsuario);
        return ResponseEntity.ok("Usuário criado com sucesso.");
    }

    /**
     * Deleta um usuário investidor pelo CPF.
     *
     * @param cpf CPF do usuário investidor
     * @return ResponseEntity com mensagem de sucesso ou erro
     */
    @Transactional
    public ResponseEntity<String> deletarPorCpf(String cpf) {
        UsuarioInvestimento usuario = usuarioInvestimentoRepository.findByCpfIdentificacao(cpf);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        usuarioInvestimentoRepository.delete(usuario);
        return ResponseEntity.ok("Usuário e seus investimentos foram deletados.");
    }
}