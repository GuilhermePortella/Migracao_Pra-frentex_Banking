package br.domain.model.operacao;

import java.time.LocalDateTime;

/**
 *
 * @author Guilherme
 */

public record OperacaoConta(Long id, TipoOperacao tipo, LocalDateTime dataHora, String descricao) {


}
