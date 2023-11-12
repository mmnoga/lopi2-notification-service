package com.liftoff.notificationservice.service.impl;

import com.liftoff.notificationservice.dto.ActivationUserData;
import com.liftoff.notificationservice.exception.BusinessException;
import com.liftoff.notificationservice.service.EncoderService;
import com.liftoff.notificationservice.service.MailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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

    @Mock
    private TemplateEngine templateEngine;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSendActivationLink() throws Exception {
        // given
        ActivationUserData activationUserData = new ActivationUserData("encodedEmail", "token");
        String decodedEmail = "test@test.com";
        String expectedSubject = "User Account Activation";
        String expectedMessage = "Your activation message";

        when(encoderService.decodeBase64(activationUserData.getEncodedUsername()))
                .thenReturn(decodedEmail);
        when(templateEngine.process(eq("email_account_activation"),
                any(Context.class))).thenReturn(expectedMessage);
        // when
        userActivationService.sendActivationLink(activationUserData);

        // then
        verify(encoderService, times(1))
                .decodeBase64(activationUserData.getEncodedUsername());
        verify(mailService, times(1))
                .sendEmail(decodedEmail, expectedSubject, expectedMessage);
    }

    @Test
    void shouldThrowBusinessExceptionWhenActivationUserDataIsNull() {
        // when and then
        assertThrows(BusinessException.class, () -> userActivationService.sendActivationLink(null));
    }

}