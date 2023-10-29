package com.liftoff.notificationservice.service.impl;

import com.liftoff.notificationservice.dto.ActivationUserData;
import com.liftoff.notificationservice.dto.ResetPasswordData;
import com.liftoff.notificationservice.service.ResetUserPasswordService;
import com.liftoff.notificationservice.service.UserActivationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class ConsumerServiceImplTest {

    @Mock
    private UserActivationService userActivationService;
    @Mock
    private ResetUserPasswordService resetUserPasswordService;

    private ConsumerServiceImpl consumerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        consumerService =
                new ConsumerServiceImpl(userActivationService, resetUserPasswordService);
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

    @Test
    void shouldConsumeResetUserPasswordData() {
        // given
        ResetPasswordData resetPasswordData =
                new ResetPasswordData("encodedEmail", "token");

        Mockito.doNothing().when(resetUserPasswordService).sendResetPasswordLink(resetPasswordData);

        // when
        consumerService.consumeResetUserPasswordData(resetPasswordData);

        // then
        Mockito.verify(resetUserPasswordService, Mockito.times(1))
                .sendResetPasswordLink(resetPasswordData);
    }

}