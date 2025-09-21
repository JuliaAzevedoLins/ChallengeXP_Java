package com.challenge.investimentos.investimentos_api.dto;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;
import com.challenge.investimentos.investimentos_api.model.UsuarioInvestimento;
import java.util.stream.Collectors;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * DTO que representa os dados de um usuário investidor,
 * incluindo o CPF de identificação e a lista de investimentos do usuário.
 */
public class UsuarioInvestimentoDTO {

    @Schema(example = "12345678900")
    @NotBlank(message = "cpfIdentificacao é obrigatório")
    @Size(min = 11, max = 14, message = "cpfIdentificacao deve ter entre 11 e 14 caracteres (apenas dígitos ou com máscara)")
    @Pattern(regexp = "^\\d{11}$|^(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2})$", message = "cpfIdentificacao deve ser 11 dígitos ou no formato XXX.XXX.XXX-XX")
    private String cpfIdentificacao;

    @Schema(
        description = "Lista de investimentos do usuário",
        example = "[{\"nomeBanco\":\"Nubank\",\"tipoInvestimento\":\"RENDA_FIXA\",\"nomeInvestimento\":\"CDB Nubank\",\"montanteInicial\":1000.0,\"valorInicialAcao\":0.0,\"taxaRentabilidade\":0.12,\"numeroAcoesInicial\":0,\"rentabilidadeDiaria\":[{\"dataRentabilidadeDiaria\":\"01-01-2025\",\"valorDiarioAcao\":100.5,\"taxaDiarioRentabilidade\":0.12,\"montanteAcumuladoDiario\":1050.75}]}]"
    )
    @Valid
    private List<InvestimentoDTO> dataUsuarioInvestimentos;

    @Schema(
        description = "Alias para a lista de investimentos esperada pelo frontend (mesmo conteúdo de dataUsuarioInvestimentos)",
        example = "[{\"nomeBanco\":\"Nubank\",\"tipoInvestimento\":\"RENDA_FIXA\",\"nomeInvestimento\":\"CDB Nubank\",\"montanteInicial\":1000.0,\"valorInicialAcao\":0.0,\"taxaRentabilidade\":0.12,\"numeroAcoesInicial\":0,\"rentabilidadeDiaria\":[{\"dataRentabilidadeDiaria\":\"01-01-2025\",\"valorDiarioAcao\":100.5,\"taxaDiarioRentabilidade\":0.12,\"montanteAcumuladoDiario\":1050.75}]}]"
    )
    @Valid
    private List<InvestimentoDTO> investimentos;

    // Getters e Setters
    public String getCpfIdentificacao() {
        return cpfIdentificacao;
    }

    public void setCpfIdentificacao(String cpfIdentificacao) {
        this.cpfIdentificacao = cpfIdentificacao;
    }

    public List<InvestimentoDTO> getDataUsuarioInvestimentos() {
        return dataUsuarioInvestimentos;
    }

    public void setDataUsuarioInvestimentos(List<InvestimentoDTO> dataUsuarioInvestimentos) {
        this.dataUsuarioInvestimentos = dataUsuarioInvestimentos;
    }

    public List<InvestimentoDTO> getInvestimentos() {
        return investimentos;
    }

    public void setInvestimentos(List<InvestimentoDTO> investimentos) {
        this.investimentos = investimentos;
    }

    /** Mapper: entidade -> DTO */
    public static UsuarioInvestimentoDTO fromEntity(UsuarioInvestimento u) {
        if (u == null) return null;
        UsuarioInvestimentoDTO dto = new UsuarioInvestimentoDTO();
        dto.setCpfIdentificacao(u.getCpfIdentificacao());
        if (u.getInvestimentos() != null) {
            List<InvestimentoDTO> mapped = u.getInvestimentos().stream()
                .map(InvestimentoDTO::fromEntity)
                .collect(Collectors.toList());
            dto.setDataUsuarioInvestimentos(mapped);
            dto.setInvestimentos(mapped);
        }
        return dto;
    }
}
