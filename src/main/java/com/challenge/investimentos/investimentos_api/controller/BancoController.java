package com.challenge.investimentos.investimentos_api.controller;

import com.challenge.investimentos.investimentos_api.dto.BancoDTO;
import com.challenge.investimentos.investimentos_api.service.BancoService;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@RestController
@RequestMapping("/api/bancos")
@Tag(name = "Bancos", description = "Endpoints para operações relacionadas a bancos")
public class BancoController {

    private final BancoService bancoService;

    public BancoController(BancoService bancoService) {
        this.bancoService = bancoService;
    }

    @Operation(
        summary = "Lista bancos por CPF",
        description = "Retorna uma lista de bancos associados ao CPF informado."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de bancos retornada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Nenhum banco encontrado para o CPF informado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{cpf}")
    public List<BancoDTO> listarBancosPorCpf(@PathVariable String cpf) {
        return bancoService.listarBancosPorCpf(cpf);
    }
}