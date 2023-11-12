package com.liftoff.notificationservice.config.rabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;

import static org.junit.jupiter.api.Assertions.*;

class RabbitMQOrderConfigTest {

    @Test
    void shouldReturnOrderQueueBean() {
        // given
        String queueName = "test_order_summary_queue";
        RabbitMQOrderConfig config =
                new RabbitMQOrderConfig(queueName, "exchangeName", "routingKey");

        // when
        Queue orderQueue = config.orderQueue();

        // then
        assertNotNull(orderQueue);
    }

    @Test
    void shouldReturnOrderExchangeBean() {
        // given
        String exchangeName = "test_order_summary_exchange";
        RabbitMQOrderConfig config = new RabbitMQOrderConfig("queueName", exchangeName, "routingKey");

        // when
        TopicExchange orderExchange = config.orderExchange();

        // then
        assertNotNull(orderExchange);
    }

    @Test
    void shouldReturnOrderBindingBean() {
        // given
        String queueName = "test_queue";
        String exchangeName = "test_exchange_name";
        String routingKey ="test_routing_key";
        RabbitMQOrderConfig config = new RabbitMQOrderConfig(queueName, exchangeName, routingKey);

        // when
        Binding orderBinding = config.orderBinding();

        // then
        assertNotNull(orderBinding);
    }

}