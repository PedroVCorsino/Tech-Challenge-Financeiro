package br.com.grupo27.techchallenge03.application.services.rabbitMQ;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.grupo27.techchallenge03.application.dto.CobrancaDTO;
import br.com.grupo27.techchallenge03.application.dto.PedidoDTO;
import br.com.grupo27.techchallenge03.domain.interfaces.usecase.PagamentoUsecase;
import br.com.grupo27.techchallenge03.domain.model.Pagamento;

@Service
public class PagamentoMessageListener {

    private static final Logger logger = LoggerFactory.getLogger(PagamentoMessageListener.class);

    @Autowired
    private PagamentoUsecase pagamentoUsecase;

    @RabbitListener(queues = "pagamentoQueue")
    public void receberPagamento(CobrancaDTO cobrancaDTO) {
        try {
            logger.info("Novo pedido recebido!");
            Pagamento pagamentoCriado = pagamentoUsecase.gerarCobranca(cobrancaDTO);
            logger.info("Pagamento para pedido nro:{} criado com sucesso!", pagamentoCriado.getIdPedido());
        } catch (Exception e) {;
            logger.info("Erro ao registrar pagamento ", e);
        }
    }
}
