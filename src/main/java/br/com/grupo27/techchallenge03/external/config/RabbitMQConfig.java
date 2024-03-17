package br.com.grupo27.techchallenge03.external.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    Queue pagamentoQueue() {
        return new Queue("pagamentoQueue", false);
    }

    @Bean
    Queue pedidoPagoQueue() {
        return new Queue("pedidoPagoQueue", false);
    }

    @Bean
    Binding pagamentoBinding(Queue pagamentoQueue, TopicExchange exchange) {
        return BindingBuilder.bind(pagamentoQueue).to(exchange).with("pagamento.#");
    }

    @Bean
    Binding pedidoPagoBinding(Queue  pedidoPagoQueue, TopicExchange exchange) {
        return BindingBuilder.bind(pedidoPagoQueue).to(exchange).with("pedidoPago.#");
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("pedidoExchange");
    }


    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

}
