package br.domain.model.usuarios;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class ContaPjTest {

    @Mock
    private UsuarioPj usuarioPj;

    private ContaPj contaPj;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        contaPj = new ContaPj(1L, usuarioPj, new BigDecimal("1000.00"), new BigDecimal("500.00"));
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals(1L, contaPj.getId());
        assertEquals(usuarioPj, contaPj.getUsuarioPj());
        assertEquals(new BigDecimal("1000.00"), contaPj.getSaldo());
        assertEquals(new BigDecimal("500.00"), contaPj.getLimiteCredito());
    }

    @Test
    public void testDepositar_ValorValido() {
        contaPj.depositar(new BigDecimal("200.00"));
        assertEquals(new BigDecimal("1200.00"), contaPj.getSaldo());
    }

    @Test
    public void testDepositar_ValorNulo() {
        assertThrows(IllegalArgumentException.class, () -> contaPj.depositar(null));
    }

    @Test
    public void testDepositar_ValorZero() {
        assertThrows(IllegalArgumentException.class, () -> contaPj.depositar(BigDecimal.ZERO));
    }

    @Test
    public void testDepositar_ValorNegativo() {
        assertThrows(IllegalArgumentException.class, () -> contaPj.depositar(new BigDecimal("-100.00")));
    }

    @Test
    public void testSacar_ValorValido() {
        contaPj.sacar(new BigDecimal("300.00"));
        assertEquals(new BigDecimal("700.00"), contaPj.getSaldo());
    }

    @Test
    public void testSacar_AteLimiteDeCredito() {
        contaPj.sacar(new BigDecimal("1500.00"));
        assertEquals(new BigDecimal("-500.00"), contaPj.getSaldo());
    }

    @Test
    public void testSacar_ExcedendoLimiteDeCredito() {
        assertThrows(IllegalStateException.class, () -> contaPj.sacar(new BigDecimal("1500.01")));
    }

    @Test
    public void testSacar_ValorNulo() {
        assertThrows(IllegalArgumentException.class, () -> contaPj.sacar(null));
    }

    @Test
    public void testSacar_ValorZero() {
        assertThrows(IllegalArgumentException.class, () -> contaPj.sacar(BigDecimal.ZERO));
    }

    @Test
    public void testSacar_ValorNegativo() {
        assertThrows(IllegalArgumentException.class, () -> contaPj.sacar(new BigDecimal("-100.00")));
    }
}
