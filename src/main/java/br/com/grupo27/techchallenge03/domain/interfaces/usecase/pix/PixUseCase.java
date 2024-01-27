package br.com.grupo27.techchallenge03.domain.interfaces.usecase.pix;

import java.util.HashMap;

import br.com.grupo27.techchallenge03.application.dto.CobrancaDTO;
import br.com.grupo27.techchallenge03.application.dto.PagamentoDTO;

public interface PixUseCase {
    HashMap<String, String> gerarCobranca(CobrancaDTO cobrancaDTO);
    String gerarQrCode(String string);
    Boolean consultaStatusPagamento(String idtx);
}
