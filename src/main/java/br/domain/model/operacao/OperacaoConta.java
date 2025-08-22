package br.domain.model.operacao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 *
 * @author Guilherme
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperacaoConta {

    private Long id;
    private TipoOperacao tipo;
    private LocalDateTime dataHora;
    private String descricao;

}
