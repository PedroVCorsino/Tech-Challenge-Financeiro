package br.com.grupo27.techchallenge03.application.services.rabbitMQ;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.grupo27.techchallenge03.application.dto.PagamentoDTO;
import br.com.grupo27.techchallenge03.domain.interfaces.services.rabbitmq.PedidoAsyncService;

@Service
public class PedidoAsyncServiceImpl implements PedidoAsyncService {
    
    private static final Logger logger = LoggerFactory.getLogger(PedidoAsyncServiceImpl.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TopicExchange exchange;


    @Override
    public void enviarParafilaDePedidosPagos(PagamentoDTO pagamentoDTO) {
        try {
            rabbitTemplate.convertAndSend(exchange.getName(), "pedidoPago.novo", pagamentoDTO);
            logger.info("Novo pedido encaminhado para fila de pedidos pagos! ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
