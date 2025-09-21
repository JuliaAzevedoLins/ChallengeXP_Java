package com.challenge.investimentos.investimentos_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;

/**
 * Valor-objeto imutável que representa um CPF válido.
 *
 * Normaliza a entrada para apenas dígitos e valida utilizando o algoritmo
 * dos dígitos verificadores (módulo 11). Usado como componente incorporável
 * em entidades JPA.
 */
@Embeddable
public final class CpfVO {
    @Column(name = "cpf", length = 11, nullable = false, updatable = false)
    private String cpf;

    /**
     * Construtor protegido exigido pelo JPA.
     */
    protected CpfVO() {}

    /**
     * Cria um novo CPF a partir da string informada.
     *
     * A entrada é normalizada para conter apenas dígitos; em seguida é
     * aplicada a validação. Caso seja inválido, uma {@link IllegalArgumentException}
     * é lançada.
     *
     * @param cpf CPF informado (com ou sem máscara)
     * @throws IllegalArgumentException se o CPF for inválido
     */
    public CpfVO(String cpf) {
        String onlyDigits = normalize(cpf);
        if (!isValidCpf(onlyDigits)) {
            throw new IllegalArgumentException("CPF inválido: " + cpf);
        }
        this.cpf = onlyDigits;
    }

    /**
     * Retorna o CPF normalizado (11 dígitos).
     * @return CPF somente com dígitos
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Remove todos os caracteres não numéricos da string.
     * @param cpf valor de entrada do CPF
     * @return apenas dígitos ou {@code null} se a entrada for {@code null}
     */
    private static String normalize(String cpf) {
        if (cpf == null) return null;
        return cpf.replaceAll("\\D", "");
    }

    /**
     * Valida o CPF utilizando o algoritmo clássico dos dígitos verificadores (módulo 11).
     * 
     * Rejeita CPFs nulos, com tamanho incorreto, com todos os dígitos iguais,
     * e verifica os dois dígitos verificadores.
     *
     * @param cpf CPF somente com dígitos
     * @return {@code true} se for válido; caso contrário {@code false}
     */
    private static boolean isValidCpf(String cpf) {
        if (cpf == null) return false;
        if (!cpf.matches("\\d{11}")) return false;
        if (cpf.matches("(\\d)\\1{10}")) return false;

        try {
            int[] digits = cpf.chars().map(c -> c - '0').toArray();
            int sum = 0;
            for (int i = 0; i < 9; i++) sum += digits[i] * (10 - i);
            int dv1 = 11 - (sum % 11);
            if (dv1 >= 10) dv1 = 0;
            if (dv1 != digits[9]) return false;

            sum = 0;
            for (int i = 0; i < 10; i++) sum += digits[i] * (11 - i);
            int dv2 = 11 - (sum % 11);
            if (dv2 >= 10) dv2 = 0;
            return dv2 == digits[10];
        } catch (Exception e) {
            return false;
        }
    }

    /** {@inheritDoc} */
    @Override public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof CpfVO)) return false;
        return Objects.equals(cpf, ((CpfVO)o).cpf);
    }
    /** {@inheritDoc} */
    @Override public int hashCode(){ return Objects.hash(cpf); }

    /**
     * Retorna o CPF em sua forma normalizada (somente dígitos).
     */
    @Override public String toString(){ return cpf; }
}
