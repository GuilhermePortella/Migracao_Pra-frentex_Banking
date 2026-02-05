package br.core.validation.cnpjvalidation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ValidatorCPNJTest {

    private final ValidatorCPNJ validator = new ValidatorCPNJ();

    @Test
    void deveAceitarCnpjNulo() {
        assertTrue(validator.isValid(null, null));
    }

    @Test
    void deveAceitarCnpjValidoComOuSemMascara() {
        assertTrue(validator.isValid("04252011000110", null));
        assertTrue(validator.isValid("04.252.011/0001-10", null));
    }

    @Test
    void deveRejeitarCnpjInvalido() {
        assertFalse(validator.isValid("11111111111111", null));
        assertFalse(validator.isValid("04252011000111", null));
        assertFalse(validator.isValid("123", null));
    }
}
