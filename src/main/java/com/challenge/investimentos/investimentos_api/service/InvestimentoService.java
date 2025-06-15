package com.challenge.investimentos.investimentos_api.service;

import com.challenge.investimentos.investimentos_api.dto.InvestimentoDTO;
import com.challenge.investimentos.investimentos_api.dto.UsuarioInvestimentoDTO;
import com.challenge.investimentos.investimentos_api.model.Investimento;
import com.challenge.investimentos.investimentos_api.model.RentabilidadeDiaria;
import com.challenge.investimentos.investimentos_api.model.UsuarioInvestimento;
import com.challenge.investimentos.investimentos_api.repository.InvestimentoRepository;
import com.challenge.investimentos.investimentos_api.repository.UsuarioInvestimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Serviço responsável pela lógica de negócios relacionada aos investimentos.
 * Permite salvar, listar, atualizar e deletar investimentos, além de buscar por CPF do usuário investidor.
 */
@Service
public class InvestimentoService {

    private final UsuarioInvestimentoRepository usuarioInvestimentoRepository;
    private final InvestimentoRepository investimentoRepository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    /**
     * Construtor para injeção de dependências.
     * @param usuarioInvestimentoRepository repositório de usuários investidores
     * @param investimentoRepository repositório de investimentos
     */
    @Autowired
    public InvestimentoService(UsuarioInvestimentoRepository usuarioInvestimentoRepository,
                               InvestimentoRepository investimentoRepository) {
        this.usuarioInvestimentoRepository = usuarioInvestimentoRepository;
        this.investimentoRepository = investimentoRepository;
    }

    /**
     * Salva ou atualiza os investimentos de um usuário investidor.
     * @param dto DTO contendo os dados do usuário e seus investimentos
     * @return ResponseEntity com mensagem de sucesso ou erro
     */
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
            investimento.setNomeBanco(investDTO.getNomeBanco());
            investimento.setCodigoBancario(investDTO.getCodigoBancario());
            investimento.setTipoInvestimento(investDTO.getTipoInvestimento());
            investimento.setNomeInvestimento(investDTO.getNomeInvestimento());
            investimento.setMontanteInicial(investDTO.getMontanteInicial());
            investimento.setValorInicialAcao(investDTO.getValorInicialAcao());
            investimento.setTaxaRentabilidade(investDTO.getTaxaRentabilidade());
            investimento.setNumeroAcoesInicial(investDTO.getNumeroAcoesInicial());

            if (investDTO.getRentabilidadeDiaria() != null && !investDTO.getRentabilidadeDiaria().isEmpty()) {
                List<RentabilidadeDiaria> rentabilidades = investDTO.getRentabilidadeDiaria().stream().map(rdDTO -> {
                    RentabilidadeDiaria rd = new RentabilidadeDiaria();
                    LocalDate data = LocalDate.parse(rdDTO.getDataRentabilidadeDiaria(), formatter);
                    rd.setDataRentabilidadeDiaria(data);
                    rd.setValorDiarioAcao(rdDTO.getValorDiarioAcao());
                    rd.setTaxaDiarioRentabilidade(rdDTO.getTaxaDiarioRentabilidade());
                    rd.setMontanteAcumuladoDiario(rdDTO.getMontanteAcumuladoDiario());
                    rd.setInvestimento(investimento);
                    return rd;
                }).collect(Collectors.toList());
                investimento.setRentabilidadeDiaria(rentabilidades);
            }

            return investimento;
        }).collect(Collectors.toList());

        investimentoRepository.saveAll(investimentos);
        return ResponseEntity.ok("Investimentos atualizados com sucesso");
    }

    /**
     * Lista todos os investimentos cadastrados.
     * @return ResponseEntity com a lista de investimentos
     */
    public ResponseEntity<List<Investimento>> listarTodos() {
        return ResponseEntity.ok(investimentoRepository.findAll());
    }

    /**
     * Lista todos os investimentos de um usuário pelo CPF.
     * @param cpf CPF do usuário investidor
     * @return ResponseEntity com a lista de investimentos do usuário ou not found se não existir
     */
    public ResponseEntity<List<Investimento>> listarPorCpf(String cpf) {
        UsuarioInvestimento usuario = usuarioInvestimentoRepository.findByCpfIdentificacao(cpf);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        List<Investimento> investimentos = investimentoRepository.findByUsuarioInvestimento(usuario);
        return ResponseEntity.ok(investimentos);
    }

    /**
     * Deleta um investimento pelo seu ID.
     * @param id ID do investimento
     * @return ResponseEntity com mensagem de sucesso ou erro
     */
    public ResponseEntity<String> deletarPorId(Long id) {
        if (!investimentoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        investimentoRepository.deleteById(id);
        return ResponseEntity.ok("Investimento deletado com sucesso");
    }

    /**
     * Atualiza um investimento existente pelo ID.
     * @param id ID do investimento a ser atualizado
     * @param dto DTO contendo os novos dados do investimento
     * @return ResponseEntity com mensagem de sucesso ou erro
     */
    public ResponseEntity<String> atualizarInvestimento(Long id, InvestimentoDTO dto) {
        Investimento investimento = investimentoRepository.findById(id).orElse(null);
        if (investimento == null) {
            return ResponseEntity.notFound().build();
        }

        investimento.setNomeBanco(dto.getNomeBanco());
        investimento.setCodigoBancario(dto.getCodigoBancario());
        investimento.setTipoInvestimento(dto.getTipoInvestimento());
        investimento.setNomeInvestimento(dto.getNomeInvestimento());
        investimento.setMontanteInicial(dto.getMontanteInicial());
        investimento.setValorInicialAcao(dto.getValorInicialAcao());
        investimento.setTaxaRentabilidade(dto.getTaxaRentabilidade());
        investimento.setNumeroAcoesInicial(dto.getNumeroAcoesInicial());

        // Atualiza rentabilidades diárias
        if (dto.getRentabilidadeDiaria() != null) {
            List<RentabilidadeDiaria> rentabilidades = dto.getRentabilidadeDiaria().stream().map(rdDTO -> {
                RentabilidadeDiaria rd = new RentabilidadeDiaria();
                rd.setDataRentabilidadeDiaria(LocalDate.parse(rdDTO.getDataRentabilidadeDiaria(), formatter));
                rd.setValorDiarioAcao(rdDTO.getValorDiarioAcao());
                rd.setTaxaDiarioRentabilidade(rdDTO.getTaxaDiarioRentabilidade());
                rd.setMontanteAcumuladoDiario(rdDTO.getMontanteAcumuladoDiario());
                rd.setInvestimento(investimento);
                return rd;
            }).collect(Collectors.toList());
            investimento.setRentabilidadeDiaria(rentabilidades);
        }

        investimentoRepository.save(investimento);
        return ResponseEntity.ok("Investimento atualizado com sucesso");
    }


    /**
     * Cria um novo investimento para um usuário investidor.
     * @param dto DTO contendo os dados do usuário e seus investimentos
     * @return ResponseEntity com mensagem de sucesso ou erro
     */
    public ResponseEntity<String> criarInvestimento(UsuarioInvestimentoDTO dto) {
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
            investimento.setNomeBanco(investDTO.getNomeBanco());
            investimento.setCodigoBancario(investDTO.getCodigoBancario());
            investimento.setTipoInvestimento(investDTO.getTipoInvestimento());
            investimento.setNomeInvestimento(investDTO.getNomeInvestimento());
            investimento.setMontanteInicial(investDTO.getMontanteInicial());
            investimento.setValorInicialAcao(investDTO.getValorInicialAcao());
            investimento.setTaxaRentabilidade(investDTO.getTaxaRentabilidade());
            investimento.setNumeroAcoesInicial(investDTO.getNumeroAcoesInicial());

            if (investDTO.getRentabilidadeDiaria() != null && !investDTO.getRentabilidadeDiaria().isEmpty()) {
                List<RentabilidadeDiaria> rentabilidades = investDTO.getRentabilidadeDiaria().stream().map(rdDTO -> {
                    RentabilidadeDiaria rd = new RentabilidadeDiaria();
                    LocalDate data = LocalDate.parse(rdDTO.getDataRentabilidadeDiaria(), formatter);
                    rd.setDataRentabilidadeDiaria(data);
                    rd.setValorDiarioAcao(rdDTO.getValorDiarioAcao());
                    rd.setTaxaDiarioRentabilidade(rdDTO.getTaxaDiarioRentabilidade());
                    rd.setMontanteAcumuladoDiario(rdDTO.getMontanteAcumuladoDiario());
                    rd.setInvestimento(investimento);
                    return rd;
                }).collect(Collectors.toList());
                investimento.setRentabilidadeDiaria(rentabilidades);
            }

            return investimento;
        }).collect(Collectors.toList());

        investimentoRepository.saveAll(investimentos);
        return ResponseEntity.status(201).body("Investimento(s) criado(s) com sucesso");
    }
}