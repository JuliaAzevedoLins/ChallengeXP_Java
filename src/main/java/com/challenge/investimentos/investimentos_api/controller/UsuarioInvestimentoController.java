package com.challenge.investimentos.investimentos_api.controller;

import com.challenge.investimentos.investimentos_api.dto.UsuarioInvestimentoDTO;
import com.challenge.investimentos.investimentos_api.model.UsuarioInvestimento;
import com.challenge.investimentos.investimentos_api.service.UsuarioInvestimentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario-investimentos")
@Tag(name = "Usuários Investidores", description = "Endpoints para operações relacionadas a usuários investidores")
public class UsuarioInvestimentoController {

    @Autowired
    private UsuarioInvestimentoService service;

    @PutMapping
    @Operation(summary = "Salvar ou atualizar investimentos do usuário", description = "Salva ou atualiza os investimentos associados a um usuário investidor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Investimentos salvos com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos enviados"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<String> salvarInvestimentos(@RequestBody UsuarioInvestimentoDTO dto) {
        return service.salvarInvestimentos(dto);
    }

    @GetMapping
    @Operation(summary = "Listar todos os usuários investidores", description = "Retorna a lista completa de usuários investidores cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<List<UsuarioInvestimento>> listarTodosUsuarios() {
        return service.listarTodosUsuarios();
    }

    @GetMapping("/{cpf}")
    @Operation(summary = "Buscar usuário investidor pelo CPF", description = "Retorna o usuário investidor que possui o CPF informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<?> buscarPorCpf(@PathVariable String cpf) {
        return service.buscarPorCpf(cpf);
    }

    @PostMapping
    @Operation(summary = "Criar novo usuário investidor", description = "Cria um novo usuário investidor com os dados fornecidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos enviados"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<String> criarUsuarioInvestimento(@RequestBody UsuarioInvestimentoDTO dto) {
        return service.criarUsuarioInvestimento(dto);
    }

    @DeleteMapping("/{cpf}")
    @Operation(summary = "Deletar usuário investidor pelo CPF", description = "Remove o usuário investidor identificado pelo CPF informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<String> deletarPorCpf(@PathVariable String cpf) {
        return service.deletarPorCpf(cpf);
    }
}
