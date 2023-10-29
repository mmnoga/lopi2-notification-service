package com.liftoff.notificationservice.config.rabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class RabbitMQResetPasswordConfigTest {

    @Test
    void shouldReturnResetPasswordQueueBean() {
        // given
        String queueName = "testResetPasswordQueue";
        String exchangeName = "testResetPasswordExchange";
        String routingKey = "testResetPasswordRoutingKey";
        RabbitMQResetPasswordConfig config =
                new RabbitMQResetPasswordConfig(queueName, exchangeName, routingKey);

        // when
        Queue resetPasswordQueue = config.resetPasswordQueue();

        // then
        assertNotNull(resetPasswordQueue);
    }

    @Test
    void shouldReturnResetPasswordExchangeBean() {
        // given
        String queueName = "testResetPasswordQueue";
        String exchangeName = "testResetPasswordExchange";
        String routingKey = "testResetPasswordRoutingKey";
        RabbitMQResetPasswordConfig config =
                new RabbitMQResetPasswordConfig(queueName, exchangeName, routingKey);

        // when
        TopicExchange resetPasswordExchange = config.resetPasswordExchange();

        // then
        assertNotNull(resetPasswordExchange);
    }

    @Test
    void shouldReturnResetPasswordBindingBean() {
        // given
        String queueName = "testResetPasswordQueue";
        String exchangeName = "testResetPasswordExchange";
        String routingKey = "testResetPasswordRoutingKey";
        RabbitMQResetPasswordConfig config =
                new RabbitMQResetPasswordConfig(queueName, exchangeName, routingKey);

        // when
        Binding resetPasswordBinding = config.resetPasswordBinding();

        // then
        assertNotNull(resetPasswordBinding);
    }

}