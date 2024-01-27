package br.com.grupo27.techchallenge03.application.usecases.pix.utils;

import org.json.JSONObject;

import br.com.grupo27.techchallenge03.application.dto.CobrancaDTO;
import br.com.grupo27.techchallenge03.external.config.Credenciais;

public class PixUtils {

    private static final String CHAVE_PIX = "4b4bd5d4-1060-4fe6-b643-562803049d66";

    private PixUtils() {
    }

    public static JSONObject configurarOpcoes(Credenciais credentials) {
        JSONObject options = new JSONObject();
        options.put("client_id", credentials.getClientId());
        options.put("client_secret", credentials.getClientSecret());
        options.put("certificate", credentials.getCertificate());
        options.put("sandbox", credentials.isSandbox());
        return options;
    }

    public static JSONObject construirCorpoCobranca(CobrancaDTO cobrancaDTO) {
        JSONObject body = new JSONObject();
        body.put("calendario", new JSONObject().put("expiracao", 3600));
        body.put("devedor", new JSONObject().put("cpf", cobrancaDTO.cliente()));
        body.put("valor", new JSONObject().put("original", cobrancaDTO.valor())); 
        body.put("chave", CHAVE_PIX);
        body.put("solicitacaoPagador", "Cobrança para o pedido id:" + cobrancaDTO.idPedido());
        return body;
    }
}
