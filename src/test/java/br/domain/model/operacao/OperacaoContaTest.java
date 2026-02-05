package br.domain.model.operacao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class OperacaoContaTest {

    @Test
    void deveCriarOperacaoComDadosInformados() {
        Long id = 1L;
        TipoOperacao tipo = TipoOperacao.CREDITO;
        LocalDateTime dataHora = LocalDateTime.now();
        String descricao = "Credito em conta";

        OperacaoConta operacao = new OperacaoConta(id, tipo, dataHora, descricao);

        assertEquals(id, operacao.id());
        assertEquals(tipo, operacao.tipo());
        assertEquals(dataHora, operacao.dataHora());
        assertEquals(descricao, operacao.descricao());
    }
}
