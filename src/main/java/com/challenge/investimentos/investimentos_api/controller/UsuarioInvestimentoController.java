package com.challenge.investimentos.investimentos_api.controller;

import com.challenge.investimentos.investimentos_api.dto.UsuarioInvestimentoDTO;
import com.challenge.investimentos.investimentos_api.model.UsuarioInvestimento;
import com.challenge.investimentos.investimentos_api.service.UsuarioInvestimentoService;
import com.challenge.investimentos.investimentos_api.dto.UsuarioCadastroDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller responsável pelos endpoints relacionados a usuários investidores.
 * Permite criar, buscar, listar, atualizar e deletar usuários investidores e seus investimentos.
 */
@RestController
@RequestMapping("/api/usuario-investimentos")
@Tag(name = "Usuários Investidores", description = "Endpoints para operações relacionadas a usuários investidores")
public class UsuarioInvestimentoController {

    @Autowired
    private UsuarioInvestimentoService service;

    /**
     * Salva ou atualiza todos os investimentos associados a um usuário investidor.
     * Essa operação sobrescreve os investimentos existentes do usuário.
     *
     * @param dto DTO contendo o CPF do usuário e a lista de investimentos
     * @return ResponseEntity com mensagem de sucesso ou erro
     */
    @PutMapping
    @Operation(
        summary = "Salvar ou atualizar todos os investimentos do usuário",
        description = "Salva ou atualiza todos os investimentos associados a um usuário investidor - Essa operação deve ser feita com cautela, pois sobrescreve os investimentos existentes"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Investimentos salvos com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos enviados"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<String> salvarInvestimentos(@RequestBody UsuarioInvestimentoDTO dto) {
        return service.salvarInvestimentos(dto);
    }

    /**
     * Lista todos os usuários investidores cadastrados.
     *
     * @return ResponseEntity com a lista de usuários investidores
     */
    @GetMapping
    @Operation(
        summary = "Listar todos os usuários investidores",
        description = "Retorna a lista completa de usuários investidores cadastrados"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<List<UsuarioInvestimento>> listarTodosUsuarios() {
        return service.listarTodosUsuarios();
    }

    /**
     * Busca um usuário investidor pelo CPF informado.
     *
     * @param cpf CPF do usuário investidor
     * @return ResponseEntity com o usuário encontrado ou mensagem de erro
     */
    @GetMapping("/{cpf}")
    @Operation(
        summary = "Buscar usuário investidor pelo CPF",
        description = "Retorna o usuário investidor que possui o CPF informado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<?> buscarPorCpf(@PathVariable String cpf) {
        return service.buscarPorCpf(cpf);
    }

    /**
     * Cria um novo usuário investidor com os dados fornecidos (apenas CPF).
     *
     * @param dto DTO contendo o CPF do novo usuário investidor
     * @return ResponseEntity com mensagem de sucesso ou erro
     */
    @PostMapping
    @Operation(
        summary = "Criar novo usuário investidor",
        description = "Cria um novo usuário investidor com os dados fornecidos (apenas CPF)"
    )
    public ResponseEntity<String> criarUsuarioInvestimento(@RequestBody UsuarioCadastroDTO dto) {
        return service.criarUsuarioInvestimento(dto.getCpfIdentificacao());
    }

    /**
     * Deleta um usuário investidor identificado pelo CPF informado.
     *
     * @param cpf CPF do usuário investidor a ser deletado
     * @return ResponseEntity com mensagem de sucesso ou erro
     */
    @DeleteMapping("/{cpf}")
    @Operation(
        summary = "Deletar usuário investidor pelo CPF",
        description = "Remove o usuário investidor identificado pelo CPF informado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<String> deletarPorCpf(@PathVariable String cpf) {
        return service.deletarPorCpf(cpf);
    }
}