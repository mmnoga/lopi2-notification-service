package com.liftoff.notificationservice.config.rabbitmq;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.MessageConverter;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class RabbitMQConfigTest {

    @Mock
    private ConnectionFactory connectionFactory;

    @Test
    void shouldReturnQueueBean() {
        // given
        RabbitMQConfig config =
                new RabbitMQConfig("testQueue", "testExchange", "testRoutingKey");

        // when
        Queue queue = config.queue();

        // then
        assertNotNull(queue);
    }

    @Test
    void shouldReturnExchangeBean() {
        // given
        RabbitMQConfig config =
                new RabbitMQConfig("testQueue", "testExchange", "testRoutingKey");

        // when
        TopicExchange exchange = config.exchange();

        // then
        assertNotNull(exchange);
    }

    @Test
    void shouldReturnBindingBean() {
        // given
        RabbitMQConfig config = new RabbitMQConfig("testQueue", "testExchange", "testRoutingKey");

        // when
        Binding binding = config.binding();

        // then
        assertNotNull(binding);
    }

    @Test
    void shouldReturnConverterBean() {
        // given
        RabbitMQConfig config =
                new RabbitMQConfig("testQueue", "testExchange", "testRoutingKey");

        // when
        MessageConverter converter = config.converter();

        // then
        assertNotNull(converter);
    }

    @Test
    void shouldReturnAmqpTemplateBean() {
        // given
        RabbitMQConfig config =
                new RabbitMQConfig("testQueue", "testExchange", "testRoutingKey");

        // when
        AmqpTemplate amqpTemplate = config.amqpTemplate(connectionFactory);

        // then
        assertNotNull(amqpTemplate);
    }

}