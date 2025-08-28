package br.domain.model.operacao;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OperacaoContaTest {

    @Test
    public void testOperacaoConta() {
        Long id = 1L;
        TipoOperacao tipo = TipoOperacao.CREDITO;
        LocalDateTime dataHora = LocalDateTime.now();
        String descricao = "Crédito em conta";

        OperacaoConta operacao = new OperacaoConta(id, tipo, dataHora, descricao);

        assertEquals(id, operacao.getId());
        assertEquals(tipo, operacao.getTipo());
        assertEquals(dataHora, operacao.getDataHora());
        assertEquals(descricao, operacao.getDescricao());
    }
}