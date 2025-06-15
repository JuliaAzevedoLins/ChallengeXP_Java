package com.challenge.investimentos.investimentos_api.controller;

import com.challenge.investimentos.investimentos_api.dto.BancoDTO;
import com.challenge.investimentos.investimentos_api.service.BancoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bancos")
public class BancoController {

    private final BancoService bancoService;

    public BancoController(BancoService bancoService) {
        this.bancoService = bancoService;
    }

    @GetMapping("/{cpf}")
    public List<BancoDTO> listarBancosPorCpf(@PathVariable String cpf) {
        return bancoService.listarBancosPorCpf(cpf);
    }
}
