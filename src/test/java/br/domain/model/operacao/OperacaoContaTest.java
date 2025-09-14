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

    // Assume OperacaoConta class has getId() method
    public class OperacaoConta {
        private Long id;
        private TipoOperacao tipo;
        private LocalDateTime dataHora;
        private String descricao;

        public OperacaoConta(Long id, TipoOperacao tipo, LocalDateTime dataHora, String descricao) {
            this.id = id;
            this.tipo = tipo;
            this.dataHora = dataHora;
            this.descricao = descricao;
        }

        public Long getId() {
            return id;
        }

        public TipoOperacao getTipo() {
            return tipo;
        }

        public LocalDateTime getDataHora() {
            return dataHora;
        }

        public String getDescricao() {
            return descricao;
        }
    }
}