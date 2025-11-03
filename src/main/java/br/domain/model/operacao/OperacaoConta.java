package br.domain.model.operacao;

import java.time.LocalDateTime;

public record OperacaoConta(Long id, TipoOperacao tipo, LocalDateTime dataHora, String descricao) {


}
