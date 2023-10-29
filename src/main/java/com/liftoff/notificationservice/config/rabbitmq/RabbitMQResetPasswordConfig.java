package com.liftoff.notificationservice.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQResetPasswordConfig {

    private final String queue;
    private final String exchange;
    private final String routingKey;

    @Autowired
    public RabbitMQResetPasswordConfig(@Value("${user.resetPassword.queue.name}") String queue,
                                       @Value("${user.resetPassword.exchange.name}") String exchange,
                                       @Value("${user.resetPassword.routing.key}") String routingKey) {
        this.queue = queue;
        this.exchange = exchange;
        this.routingKey = routingKey;
    }

    @Bean
    public Queue resetPasswordQueue() {
        return new Queue(queue);
    }

    @Bean
    public TopicExchange resetPasswordExchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    public Binding resetPasswordBinding() {
        return BindingBuilder.bind(resetPasswordQueue())
                .to(resetPasswordExchange())
                .with(routingKey);
    }

}