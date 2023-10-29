package com.liftoff.notificationservice;

import com.liftoff.notificationservice.config.rabbitmq.RabbitTestConfig;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.test.context.SpringRabbitTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = RabbitTestConfig.class)
class NotificationserviceApplicationTest {

    @Test
    void contextLoads() {
        NotificationserviceApplication.main(new String[]{});
    }

}