package br.core.validation.cpfvalidation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ValidatorCPFTest {

    private final ValidatorCPF validator = new ValidatorCPF();

    @Test
    void deveAceitarCpfNuloOuVazio() {
        assertTrue(validator.isValid(null, null));
        assertTrue(validator.isValid("", null));
    }

    @Test
    void deveAceitarCpfValidoComOuSemMascara() {
        assertTrue(validator.isValid("52998224725", null));
        assertTrue(validator.isValid("529.982.247-25", null));
    }

    @Test
    void deveRejeitarCpfInvalido() {
        assertFalse(validator.isValid("11111111111", null));
        assertFalse(validator.isValid("52998224724", null));
        assertFalse(validator.isValid("123", null));
    }
}
