package com.challenge.investimentos.investimentos_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public final class CpfVO {
    @Column(name = "cpf", length = 11, nullable = false, updatable = false)
    private String cpf;

    // JPA precisa desse construtor
    protected CpfVO() {}

    public CpfVO(String cpf) {
        String onlyDigits = normalize(cpf);
        if (!isValidCpf(onlyDigits)) {
            throw new IllegalArgumentException("CPF inválido: " + cpf);
        }
        this.cpf = onlyDigits;
    }

    public String getCpf() {
        return cpf;
    }

    private static String normalize(String cpf) {
        if (cpf == null) return null;
        return cpf.replaceAll("\\D", "");
    }

    // algoritmo clássico dos dígitos verificadores (MOD 11)
    private static boolean isValidCpf(String cpf) {
        if (cpf == null) return false;
        if (!cpf.matches("\\d{11}")) return false;
        // rejeita sequências repetidas
        if (cpf.matches("(\\d)\\1{10}")) return false;

        try {
            int[] digits = cpf.chars().map(c -> c - '0').toArray();
            // primeiro verificador (posição 9)
            int sum = 0;
            for (int i = 0; i < 9; i++) sum += digits[i] * (10 - i);
            int dv1 = 11 - (sum % 11);
            if (dv1 >= 10) dv1 = 0;
            if (dv1 != digits[9]) return false;

            // segundo verificador
            sum = 0;
            for (int i = 0; i < 10; i++) sum += digits[i] * (11 - i);
            int dv2 = 11 - (sum % 11);
            if (dv2 >= 10) dv2 = 0;
            return dv2 == digits[10];
        } catch (Exception e) {
            return false;
        }
    }

    @Override public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof CpfVO)) return false;
        return Objects.equals(cpf, ((CpfVO)o).cpf);
    }
    @Override public int hashCode(){ return Objects.hash(cpf); }

    @Override public String toString(){ return cpf; }
}
