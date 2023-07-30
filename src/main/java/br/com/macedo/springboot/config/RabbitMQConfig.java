package br.com.macedo.springboot.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq-params.queue-name}")
    private String queue;

    @Value("${rabbitmq-params.queue-name-json}")
    private String jsonQueue;

    @Value("${rabbitmq-params.exchange-name}")
    private String exchange;

    @Value("${rabbitmq-params.routing-key-name}")
    private String routingKey;

    @Value("${rabbitmq-params.routing-key-name-json}")
    private String routingKeyJson;

    //TODO: Spring Bean for RabbitMQ Queue
    @Bean
    public Queue queue() {
        return new Queue(queue);
    }

    //TODO: Spring Bean for Queue (store json messages)
    @Bean
    public Queue queueJson() {
        return new Queue(jsonQueue);
    }

    //TODO: Spring Bean for RabbitMQ Exchange
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    //TODO: Biding between queue and exchange using routing key
    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(routingKey);
    }

    //TODO: Biding between queueJson and exchange using routing key
    @Bean
    public Binding bindingJson() {
        return BindingBuilder
                .bind(queueJson())
                .to(exchange())
                .with(routingKeyJson);
    }

    //TODO: OBS -> When we use Bean annotation the springboot autoconfigure these follow components for us:
    // 1. ConnectionFactory
    // 2. RabbitTemplate
    // 3. RabbitAdmin

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());

        return rabbitTemplate;
    }

}