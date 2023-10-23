package com.liftoff.notificationservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class NotificationserviceApplicationTest {

    @Test
    void contextLoads() {
        NotificationserviceApplication.main(new String[]{});
        assertNotNull(NotificationserviceApplication.class);
    }

}