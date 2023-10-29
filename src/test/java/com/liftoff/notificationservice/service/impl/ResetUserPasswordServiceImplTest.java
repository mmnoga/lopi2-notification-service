package com.liftoff.notificationservice.service.impl;

import com.liftoff.notificationservice.dto.ResetPasswordData;
import com.liftoff.notificationservice.exception.BusinessException;
import com.liftoff.notificationservice.exception.TechnicalException;
import com.liftoff.notificationservice.service.EncoderService;
import com.liftoff.notificationservice.service.MailService;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ResetUserPasswordServiceImplTest {

    @InjectMocks
    private ResetUserPasswordServiceImpl resetUserPasswordService;

    @Mock
    private MailService mailService;

    @Mock
    private EncoderService encoderService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSendResetPasswordLink() throws Exception {
        // given
        ResetPasswordData resetPasswordData = new ResetPasswordData("encodedEmail", "token");
        String decodedEmail = "test@test.com";

        when(encoderService.decodeBase64(resetPasswordData.getEncodedUsername())).thenReturn(decodedEmail);

        // when
        resetUserPasswordService.sendResetPasswordLink(resetPasswordData);

        // then
        verify(encoderService).decodeBase64(resetPasswordData.getEncodedUsername());
        verify(mailService).sendEmail(eq(decodedEmail), anyString(), anyString());
    }

    @Test
    void shouldThrowBusinessExceptionWhenResetPasswordDataIsNull() {
        // when and then
        assertThrows(BusinessException.class, () -> resetUserPasswordService.sendResetPasswordLink(null));
    }

    @Test
    void shouldThrowTechnicalExceptionWhenMessagingExceptionOccurs() throws Exception {
        // given
        ResetPasswordData resetPasswordData = new ResetPasswordData("encodedEmail", "token");
        String decodedEmail = "test@test.com";
        when(encoderService.decodeBase64(resetPasswordData.getEncodedUsername())).thenReturn(decodedEmail);
        doThrow(new MessagingException("Test MessagingException"))
                .when(mailService).sendEmail(eq(decodedEmail), anyString(), anyString());

        // when and then
        assertThrows(TechnicalException.class, () -> resetUserPasswordService.sendResetPasswordLink(resetPasswordData));
    }

}