package br.com.macedo.springboot.consumer;

import br.com.macedo.springboot.dto.UserDTO;
import br.com.macedo.springboot.publisher.RabbitMQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonConsumer {

    private static final Logger log = LoggerFactory.getLogger(RabbitMQProducer.class);

    @RabbitListener(queues = {"${rabbitmq-params.queue-name-json}"})
    public void consume(UserDTO userDTO) {
        log.info(String.format("Received JSON message -> %s", userDTO.toString()));
    }
}
