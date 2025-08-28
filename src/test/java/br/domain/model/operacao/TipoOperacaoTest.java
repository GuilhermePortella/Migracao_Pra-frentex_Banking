package br.domain.model.operacao;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TipoOperacaoTest {

    @Test
    public void testEnumConstants() {
        assertNotNull(TipoOperacao.CREDITO);
        assertNotNull(TipoOperacao.DEBITO);
        assertNotNull(TipoOperacao.CONSULTA_CONTA);
    }

    @Test
    public void testValueOf() {
        assertEquals(TipoOperacao.CREDITO, TipoOperacao.valueOf("CREDITO"));
        assertEquals(TipoOperacao.DEBITO, TipoOperacao.valueOf("DEBITO"));
        assertEquals(TipoOperacao.CONSULTA_CONTA, TipoOperacao.valueOf("CONSULTA_CONTA"));
    }
}
