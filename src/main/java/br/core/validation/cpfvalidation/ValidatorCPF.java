package br.core.validation.cpfvalidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidatorCPF implements ConstraintValidator<ValidCPF, String> {

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext context) {
        if (cpf == null || cpf.isEmpty()) {
            return true;
        }

        String cleanedCpf = cpf.replaceAll("\\D", "");

        if (cleanedCpf.length() != 11 || hasAllSameDigits(cleanedCpf)) {
            return false;
        }

        try {
            String baseCpf = cleanedCpf.substring(0, 9);
            String primeiroDigito = String.valueOf(calcularDigitoVerificador(baseCpf));
            String segundoDigito = String.valueOf(calcularDigitoVerificador(baseCpf + primeiroDigito));

            return cleanedCpf.endsWith(primeiroDigito + segundoDigito);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean hasAllSameDigits(String cpf) {
        return cpf.chars().distinct().count() == 1;
    }

    private int calcularDigitoVerificador(String base) {
        int soma = 0;
        int peso = base.length() + 1;
        for (int i = 0; i < base.length(); i++) {
            soma += Character.getNumericValue(base.charAt(i)) * peso--;
        }

        int resto = soma % 11;
        return (resto < 2) ? 0 : 11 - resto;
    }

}
