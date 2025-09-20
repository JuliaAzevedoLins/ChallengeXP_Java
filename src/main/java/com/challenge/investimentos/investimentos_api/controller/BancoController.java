package com.challenge.investimentos.investimentos_api.controller;

import com.challenge.investimentos.investimentos_api.service.BancoService;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

/**
 * Controller responsável pelos endpoints relacionados a operações com bancos.
 */
@RestController
@RequestMapping("/api/bancos")
@Tag(name = "Bancos", description = "Endpoints para operações relacionadas a bancos")
public class BancoController {

    private final BancoService bancoService;

    /**
     * Construtor para injeção do serviço de bancos.
     * @param bancoService serviço de banco
     */
    public BancoController(BancoService bancoService) {
        this.bancoService = bancoService;
    }

    /**
     * Lista os bancos associados ao CPF informado.
     *
     * @param cpf CPF do usuário investidor
     * @return Lista de nomes de bancos (String) associados ao CPF
     */
    @Operation(
        summary = "Lista bancos por CPF",
        description = "Retorna uma lista de nomes de bancos associados ao CPF informado."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de bancos retornada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Nenhum banco encontrado para o CPF informado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{cpf}")
    public List<String> listarBancosPorCpf(@PathVariable String cpf) { // <-- Tipo de retorno alterado para List<String>
        return bancoService.listarBancosPorCpf(cpf);
    }
}