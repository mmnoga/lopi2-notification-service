package com.liftoff.notificationservice.service.impl;

import com.liftoff.notificationservice.dto.ActivationUserData;
import com.liftoff.notificationservice.service.EncoderService;
import com.liftoff.notificationservice.service.MailService;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserActivationServiceImplTest {

    @InjectMocks
    private UserActivationServiceImpl userActivationService;

    @Mock
    private MailService mailService;

    @Mock
    private EncoderService encoderService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSendActivationLink() throws Exception {
        // given
        ActivationUserData activationUserData =
                new ActivationUserData("encodedEmail", "token");
        String decodedEmail = "test@test.com";

        when(encoderService.decodeBase64(activationUserData.getEncodedUsername())).thenReturn(decodedEmail);

        // when
        userActivationService.sendActivationLink(activationUserData);

        // then
        verify(encoderService, times(1))
                .decodeBase64(activationUserData.getEncodedUsername());
        verify(mailService, times(1))
                .sendEmail(eq(decodedEmail), anyString(), anyString());
    }

    @Test
    void shouldThrowRuntimeExceptionWhenMessagingExceptionOccurs() throws Exception {
        // given
        ActivationUserData activationUserData =
                new ActivationUserData("encodedEmail", "token");
        String decodedEmail = "test@test.com";
        when(encoderService.decodeBase64(activationUserData.getEncodedUsername())).thenReturn(decodedEmail);
        doThrow(new MessagingException("Test MessagingException"))
                .when(mailService).sendEmail(eq(decodedEmail), anyString(), anyString());

        // when and then
        assertThrows(RuntimeException.class, () -> userActivationService.sendActivationLink(activationUserData));
    }

}