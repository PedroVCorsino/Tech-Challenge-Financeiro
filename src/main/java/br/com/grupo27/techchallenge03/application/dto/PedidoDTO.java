package br.com.grupo27.techchallenge03.application.dto;

import java.math.BigDecimal;
import java.util.List;

import br.com.grupo27.techchallenge03.domain.enums.StatusPedido;

import java.util.Date;

public record PedidoDTO(
    Long id,
    Long idCliente,
    List<ComboDTO> combos,
    BigDecimal valorTotal,
    StatusPedido status,
    boolean pago,
    Date dataCadastro,
    Date dataAlteracao
) {

}
