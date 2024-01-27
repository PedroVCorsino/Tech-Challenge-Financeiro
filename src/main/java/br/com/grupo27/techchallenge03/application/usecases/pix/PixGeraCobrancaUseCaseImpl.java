package br.com.grupo27.techchallenge03.application.usecases.pix;

import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;
import br.com.grupo27.techchallenge03.application.dto.CobrancaDTO;
import br.com.grupo27.techchallenge03.application.dto.PagamentoDTO;
import br.com.grupo27.techchallenge03.application.usecases.pix.utils.PixUtils;
import br.com.grupo27.techchallenge03.domain.interfaces.usecase.pix.PixGeraCobrancaUseCase;
import br.com.grupo27.techchallenge03.domain.model.Pagamento;
import br.com.grupo27.techchallenge03.domain.valuesObjects.ValidadorCPF;
import br.com.grupo27.techchallenge03.external.config.Credenciais;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

public class PixGeraCobrancaUseCaseImpl implements PixGeraCobrancaUseCase{
    private final Credenciais credentials;
    private static final Logger logger = LoggerFactory.getLogger(PixGeraCobrancaUseCaseImpl.class);

    public PixGeraCobrancaUseCaseImpl(Credenciais credentials) {
        this.credentials = credentials;
    }

    @Override
    public HashMap<String, String> registraCobranca(CobrancaDTO cobrancaDTO){
        JSONObject response = gerarCobranca(cobrancaDTO);
        String id = extrairIdDoResponse(response, "id");
        String txid = extrairTxIdDoResponse(response, "txid");
        HashMap<String, String> ids =  new HashMap<>();
        ids.put("idCobranca", id);
        ids.put("txid", txid);
        return ids;
    }

    private JSONObject gerarCobranca(CobrancaDTO cobrancaDTO) {
        JSONObject options = configurarOpcoes();
        JSONObject body = construirCorpoCobranca(cobrancaDTO);

        try {
            EfiPay efi = new EfiPay(options);
            JSONObject response = efi.call("pixCreateImmediateCharge", new HashMap<>(), body);
            logger.info("EfiPay: {}", response);

            return response;
        } catch (EfiPayException e) {
            logger.error("Erro ao criar cobrança PIX: {}", e.getErrorDescription());
            return null;
        } catch (Exception e) {
            logger.error("Erro desconhecido: {}", e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private String extrairTxIdDoResponse(JSONObject response, String key) {
        if (response != null && response.has(key)) {
            return response.getString(key);
        } else {
            logger.error("O JSON de resposta não contém a chave '{}' ou é nulo.", key);
            return null;
        }
    }

    private String extrairIdDoResponse(JSONObject response, String key) {
        if (response != null && response.has("loc") && response.getJSONObject("loc").has(key)) {
            return String.valueOf(response.getJSONObject("loc").getLong(key));
        } else {
            logger.error("O JSON de resposta não contém a chave '{}' ou é nulo.", key);
            return null;
        }
    }
    
    private JSONObject configurarOpcoes() {
        return PixUtils.configurarOpcoes(credentials);
    }

    private JSONObject construirCorpoCobranca(CobrancaDTO cobrancaDTO) {
        return PixUtils.construirCorpoCobranca(cobrancaDTO);
    }
}
