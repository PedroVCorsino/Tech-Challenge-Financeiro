package br.com.grupo27.techchallenge03.adapters.gateways;

import br.com.grupo27.techchallenge03.application.dto.PagamentoDTO;
import br.com.grupo27.techchallenge03.domain.model.Pagamento;

public interface PagamentoGateway {
    
    Pagamento savePagamento(Pagamento pagamento);

    Pagamento updatePagamento(Pagamento pagamento);

    Pagamento findPagamentoByIdPedido(Long id);

}
