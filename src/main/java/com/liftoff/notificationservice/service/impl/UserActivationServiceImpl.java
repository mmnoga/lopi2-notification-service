package com.liftoff.notificationservice.service.impl;

import com.liftoff.notificationservice.config.constants.ActivationConst;
import com.liftoff.notificationservice.dto.ActivationUserData;
import com.liftoff.notificationservice.exception.BusinessException;
import com.liftoff.notificationservice.exception.TechnicalException;
import com.liftoff.notificationservice.service.EncoderService;
import com.liftoff.notificationservice.service.MailService;
import com.liftoff.notificationservice.service.UserActivationService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.net.URI;

@Service
public class UserActivationServiceImpl implements UserActivationService {

    private final TemplateEngine templateEngine;
    private final MailService mailService;
    private final EncoderService encoderService;
    private final String activationLinkBase;
    private final String activationPath;

    public UserActivationServiceImpl(
            TemplateEngine templateEngine,
            MailService mailService,
            EncoderService encoderService,
            @Value("${user.register.activation-link-base}") String activationLinkBase,
            @Value("${user.register.activation-path}") String activationPath) {
        this.templateEngine = templateEngine;
        this.mailService = mailService;
        this.encoderService = encoderService;
        this.activationLinkBase = activationLinkBase;
        this.activationPath = activationPath;
    }

    @Override
    public void sendActivationLink(ActivationUserData activationUserData) {

        if (activationUserData == null) {
            throw new BusinessException("activationUserData cannot be null", HttpStatus.BAD_REQUEST);
        }

        String encodedEmail = activationUserData.getEncodedUsername();
        String token = activationUserData.getTokenValue();
        String email = encoderService.decodeBase64(encodedEmail);

        String query = String.format("token=%s&email=%s", token, encodedEmail);

        URI activationURI = UriComponentsBuilder.fromPath(activationPath)
                .query(query)
                .build()
                .toUri();
        String activationLink = activationLinkBase + activationURI;
        Context context = new Context();
        context.setVariable("activationLink", activationLink);

        String subject = ActivationConst.ACTIVATION_SUBJECT_TEMPLATE;

        String message = templateEngine
                .process("email_account_activation", context);

        try {
            mailService.sendEmail(email, subject, message);
        } catch (MessagingException e) {
            throw new TechnicalException("An error occurred while sending the email",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}