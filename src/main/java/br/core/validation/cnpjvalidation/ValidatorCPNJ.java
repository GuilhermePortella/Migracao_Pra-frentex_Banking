package br.core.validation.cnpjvalidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidatorCPNJ implements ConstraintValidator<ValidCNPJ, String> {

    private static final int[] WEIGHTS_DIGIT_1 = { 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
    private static final int[] WEIGHTS_DIGIT_2 = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

    @Override
    public boolean isValid(String cnpj, ConstraintValidatorContext context) {
        if (cnpj == null) {
            return true;
        }

        String cleanedCnpj = cnpj.replaceAll("\\D", "");

        if (cleanedCnpj.length() != 14 || hasAllSameDigits(cleanedCnpj)) {
            return false;
        }

        try {
            String firstDigit = String.valueOf(calculateDigit(cleanedCnpj.substring(0, 12), WEIGHTS_DIGIT_1));
            if (!cleanedCnpj.substring(12, 13).equals(firstDigit)) {
                return false;
            }

            String secondDigit = String.valueOf(calculateDigit(cleanedCnpj.substring(0, 13), WEIGHTS_DIGIT_2));
            return cleanedCnpj.substring(13, 14).equals(secondDigit);

        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean hasAllSameDigits(String cnpj) {
        return cnpj.chars().distinct().count() == 1;
    }

    private int calculateDigit(String base, int[] weights) {
        int sum = 0;
        for (int i = 0; i < base.length(); i++) {
            sum += Character.getNumericValue(base.charAt(i)) * weights[i];
        }
        int remainder = sum % 11;
        return (remainder < 2) ? 0 : 11 - remainder;
    }
}
