package com.challenge.investimentos.investimentos_api.enums;

/**
 * Enum que representa os bancos e seus códigos.
 * Útil para preencher automaticamente o código do banco pelo nome.
 */
public enum BancoEnum {
    NUBANK("Nubank", 260),
    ITAU("Itaú", 341),
    BRADESCO("Bradesco", 237),
    SANTANDER("Santander", 33),
    CAIXA("Caixa Econômica", 104),
    BANCO_DO_BRASIL("Banco do Brasil", 1),
    INTER("Inter", 77),
    BTG_PACTUAL("BTG Pactual", 208),
    XP_INVESTIMENTOS("XP Investimentos", 348),
    C6_BANK("C6 Bank", 336);

    private final String nomeBanco;
    private final int codigoBancario;

    BancoEnum(String nomeBanco, int codigoBancario) {
        this.nomeBanco = nomeBanco;
        this.codigoBancario = codigoBancario;
    }

    public String getNomeBanco() {
        return nomeBanco;
    }

    public int getCodigoBancario() {
        return codigoBancario;
    }

    /**
     * Retorna o código do banco dado o nome.
     * @param nomeBanco nome do banco
     * @return código do banco
     */
    public static int getCodigoPorNome(String nomeBanco) {
        for (BancoEnum banco : BancoEnum.values()) {
            if (banco.getNomeBanco().equalsIgnoreCase(nomeBanco)) {
                return banco.getCodigoBancario();
            }
        }
        throw new IllegalArgumentException("Banco não encontrado: " + nomeBanco);
    }
}