package br.core.validation.cnpjvalidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidatorCPNJ implements ConstraintValidator<ValidCNPJ, String> {

    @Override
    public boolean isValid(String cnpj, ConstraintValidatorContext context) {
        if (cnpj == null) {
            return true;
        }
        
        cnpj = cnpj.replaceAll("[^\\d]", "");

        if (cnpj.length() != 14 || cnpj.matches("(\\d)\\1{13}")) return false;

        int[] pesos1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int soma = 0;
        for (int i = 0; i < 12; i++) {
            soma += Character.getNumericValue(cnpj.charAt(i)) * pesos1[i];
        }
        int resto = soma % 11;
        char digito1 = (resto < 2) ? '0' : (char) ((11 - resto) + '0');
        if (digito1 != cnpj.charAt(12)) return false;

        int[] pesos2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        soma = 0;
        for (int i = 0; i < 13; i++) {
            soma += Character.getNumericValue(cnpj.charAt(i)) * pesos2[i];
        }
        resto = soma % 11;
        char digito2 = (resto < 2) ? '0' : (char) ((11 - resto) + '0');

        return digito2 == cnpj.charAt(13);
    }
}
