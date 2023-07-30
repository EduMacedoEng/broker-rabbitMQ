package br.com.macedo.springboot.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq-params.queue-name}")
    private String queue;

    @Value("${rabbitmq-params.exchange-name}")
    private String exchange;

    @Value("${rabbitmq-params.routing-key-name}")
    private String routingKey;

    //TODO: Spring Bean for RabbitMQ Queue
    @Bean
    public Queue queue() {
        return new Queue(queue);
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

    //TODO: OBS -> When we use Bean annotation the springboot autoconfigure these follow components for us:
    // 1. ConnectionFactory
    // 2. RabbitTemplate
    // 3. RabbitAdmin

}