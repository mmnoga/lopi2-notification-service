package com.liftoff.notificationservice.service.impl;

import com.liftoff.notificationservice.dto.ActivationUserData;
import com.liftoff.notificationservice.service.UserActivationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class ConsumerServiceImplTest {

    @Mock
    private UserActivationService userActivationService;

    private ConsumerServiceImpl consumerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        consumerService = new ConsumerServiceImpl(userActivationService);
    }

    @Test
    void shouldConsumeActivationUserData() {
        // given
        ActivationUserData activationUserData =
                new ActivationUserData("encodedEmail", "token");

        Mockito.doNothing().when(userActivationService).sendActivationLink(activationUserData);

        // when
        consumerService.consumeActivationUserData(activationUserData);

        // then
        Mockito.verify(userActivationService,
                Mockito.times(1)).sendActivationLink(activationUserData);
    }

}