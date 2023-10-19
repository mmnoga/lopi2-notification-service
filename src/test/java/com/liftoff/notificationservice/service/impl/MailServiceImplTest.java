package com.liftoff.notificationservice.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MailServiceImplTest {

    @InjectMocks
    private MailServiceImpl mailService;

    @Mock
    private JavaMailSender mailSender;

    @BeforeEach
    void setup() {
        mailSender = mock(JavaMailSender.class);
        mailService = new MailServiceImpl(mailSender, "test@example.com");
    }

    @Test
    void shouldSendEmail() throws MessagingException {
        // given
        String to = "test@test.pl";
        String subject = "Test Subject";
        String message = "Test Message";

        MimeMessage mimeMessage = mock(MimeMessage.class);

        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);

        // when
        mailService.sendEmail(to, subject, message);

        // then
        verify(mailSender).send(mimeMessage);
    }

}