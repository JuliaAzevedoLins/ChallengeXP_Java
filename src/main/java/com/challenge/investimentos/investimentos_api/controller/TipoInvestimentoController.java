package com.challenge.investimentos.investimentos_api.controller;

import com.challenge.investimentos.investimentos_api.dto.TipoInvestimentoDTO;
import com.challenge.investimentos.investimentos_api.service.TipoInvestimentoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-investimento")
public class TipoInvestimentoController {

    private final TipoInvestimentoService tipoInvestimentoService;

    public TipoInvestimentoController(TipoInvestimentoService tipoInvestimentoService) {
        this.tipoInvestimentoService = tipoInvestimentoService;
    }

    @GetMapping("/{cpf}")
    public List<TipoInvestimentoDTO> listarTiposPorCpf(@PathVariable String cpf) {
        return tipoInvestimentoService.listarTiposPorCpf(cpf);
    }
}
