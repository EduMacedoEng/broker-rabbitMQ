package br.com.macedo.springboot.publisher;

import br.com.macedo.springboot.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonProducer {

    @Value("${rabbitmq-params.exchange-name}")
    private String exchange;

    @Value("${rabbitmq-params.routing-key-name-json}")
    private String routingKeyJson;

    private static final Logger log = LoggerFactory.getLogger(RabbitMQProducer.class);

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(UserDTO userDTO) {
        log.info(String.format("Json message sent -> %s", userDTO.toString()));
        rabbitTemplate.convertAndSend(exchange, routingKeyJson, userDTO);
    }
}
