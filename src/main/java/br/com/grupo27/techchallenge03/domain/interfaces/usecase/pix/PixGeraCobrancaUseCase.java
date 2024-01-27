package br.com.grupo27.techchallenge03.domain.interfaces.usecase.pix;

import java.util.HashMap;

import br.com.grupo27.techchallenge03.application.dto.CobrancaDTO;
import br.com.grupo27.techchallenge03.application.dto.PagamentoDTO;


public interface PixGeraCobrancaUseCase {
    public HashMap<String, String>registraCobranca(CobrancaDTO cobrancaDTO);

}
