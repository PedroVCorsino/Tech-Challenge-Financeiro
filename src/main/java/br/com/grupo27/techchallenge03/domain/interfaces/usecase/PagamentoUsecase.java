package br.com.grupo27.techchallenge03.domain.interfaces.usecase;

import java.util.List;

import br.com.grupo27.techchallenge03.application.dto.CobrancaDTO;
import br.com.grupo27.techchallenge03.application.dto.PagamentoDTO;
import br.com.grupo27.techchallenge03.domain.model.Pagamento;

public interface PagamentoUsecase {
    
    Boolean consultaStatusPagamento(Long id);

    Boolean verificaStatusPagamento(Long id);

    // List<PedidoDTO> findPedidosByStatusPagamento(boolean pago);

    String geraQrCodePedido(Long id);
    Pagamento gerarCobranca(CobrancaDTO cobrancaDTODTO);
}
