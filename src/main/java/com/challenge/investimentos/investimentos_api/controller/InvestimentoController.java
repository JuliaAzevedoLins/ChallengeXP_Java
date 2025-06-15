package com.challenge.investimentos.investimentos_api.controller;

import com.challenge.investimentos.investimentos_api.dto.InvestimentoDTO;
import com.challenge.investimentos.investimentos_api.dto.UsuarioInvestimentoDTO;
import com.challenge.investimentos.investimentos_api.model.Investimento;
import com.challenge.investimentos.investimentos_api.service.InvestimentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller responsável pelos endpoints relacionados a investimentos.
 */
@RestController
@RequestMapping("/api/investimentos")
@Tag(name = "Investimentos", description = "Endpoints para operações relacionadas a investimentos")
public class InvestimentoController {

    private final InvestimentoService investimentoService;

    /**
     * Injeta o serviço de investimentos.
     * @param investimentoService serviço de investimentos
     */
    @Autowired
    public InvestimentoController(InvestimentoService investimentoService) {
        this.investimentoService = investimentoService;
    }

    /**
     * Cria um novo investimento para um usuário.
     * @param dto DTO contendo os dados do usuário e seus investimentos
     * @return ResponseEntity com mensagem de sucesso ou erro
     */
    @PostMapping
    @Operation(summary = "Criar novo investimento para o usuário", description = "Cria um novo investimento para o usuário informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Investimento criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos enviados"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<String> criarInvestimento(@RequestBody UsuarioInvestimentoDTO dto) {
        return investimentoService.criarInvestimento(dto);
    }

    /**
     * Salva ou atualiza os investimentos de um usuário.
     * @param dto DTO contendo os dados do usuário e seus investimentos
     * @return ResponseEntity com mensagem de sucesso ou erro
     */
    @PutMapping
    @Operation(summary = "Salvar ou atualizar investimentos do usuário", description = "Recebe os dados de investimentos e salva ou atualiza para o usuário informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Investimentos salvos com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos enviados"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<String> salvarInvestimentos(@RequestBody UsuarioInvestimentoDTO dto) {
        return investimentoService.salvarInvestimentos(dto);
    }

    /**
     * Atualiza um investimento existente pelo ID.
     * @param id ID do investimento
     * @param dto DTO contendo os novos dados do investimento
     * @return ResponseEntity com mensagem de sucesso ou erro
     */
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar investimento pelo ID", description = "Atualiza os dados de um investimento específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Investimento atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Investimento não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos enviados"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<String> atualizarInvestimento(@PathVariable Long id, @RequestBody InvestimentoDTO dto) {
        return investimentoService.atualizarInvestimento(id, dto);
    }

    /**
     * Lista todos os investimentos cadastrados.
     * @return ResponseEntity com a lista de investimentos
     */
    @GetMapping
    @Operation(summary = "Listar todos os investimentos", description = "Retorna uma lista com todos os investimentos cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de investimentos retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<List<Investimento>> listarTodosInvestimentos() {
        return investimentoService.listarTodos();
    }

    /**
     * Lista todos os investimentos de um usuário pelo CPF.
     * @param cpf CPF do usuário
     * @return ResponseEntity com a lista de investimentos do usuário
     */
    @GetMapping("/usuario/{cpf}")
    @Operation(summary = "Listar investimentos por CPF do usuário", description = "Retorna uma lista de investimentos do usuário informado pelo CPF")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de investimentos do usuário retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<List<Investimento>> listarPorCpf(@PathVariable String cpf) {
        return investimentoService.listarPorCpf(cpf);
    }

    /**
     * Deleta um investimento pelo seu ID.
     * @param id ID do investimento
     * @return ResponseEntity com mensagem de sucesso ou erro
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar investimento pelo ID", description = "Remove um investimento específico pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Investimento deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Investimento não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<String> deletarInvestimento(@PathVariable Long id) {
        return investimentoService.deletarPorId(id);
    }
}