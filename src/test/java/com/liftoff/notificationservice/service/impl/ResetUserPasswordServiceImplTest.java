package com.liftoff.notificationservice.service.impl;

import com.liftoff.notificationservice.dto.ResetPasswordData;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ResetUserPasswordServiceImplTest {

    @InjectMocks
    private ResetUserPasswordServiceImpl resetUserPasswordService;

    @Mock
    private TemplateEngine templateEngine;

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
        ResetPasswordData resetPasswordData =
                new ResetPasswordData("encodedEmail", "token");
        String decodedEmail = "test@test.com";
        String expectedMessage = "test_message";

        when(encoderService.decodeBase64(resetPasswordData.getEncodedUsername())).thenReturn(decodedEmail);
        when(templateEngine.process(eq("email_reset_password"), any(Context.class))).thenReturn(expectedMessage);

        // when
        resetUserPasswordService.sendResetPasswordLink(resetPasswordData);

        // then
        verify(encoderService).decodeBase64(resetPasswordData.getEncodedUsername());
        verify(mailService).sendEmail(eq(decodedEmail), anyString(), eq(expectedMessage));
    }

    @Test
    void shouldThrowBusinessExceptionWhenResetPasswordDataIsNull() {
        // when and then
        assertThrows(BusinessException.class, () -> resetUserPasswordService.sendResetPasswordLink(null));
    }

}