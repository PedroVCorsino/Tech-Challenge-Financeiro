package br.com.grupo27.techchallenge03.domain.interfaces.services.rabbitmq;

import br.com.grupo27.techchallenge03.application.dto.CobrancaDTO;
import br.com.grupo27.techchallenge03.application.dto.PagamentoDTO;
import br.com.grupo27.techchallenge03.application.dto.PedidoDTO;

public interface PedidoAsyncService {
     public void enviarParafilaDePedidosPagos(PagamentoDTO pagamentoDTO);
}
