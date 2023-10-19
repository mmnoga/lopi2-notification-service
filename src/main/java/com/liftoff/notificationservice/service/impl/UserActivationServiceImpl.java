package com.liftoff.notificationservice.service.impl;

import com.liftoff.notificationservice.config.constants.ActivationConst;
import com.liftoff.notificationservice.dto.ActivationUserData;
import com.liftoff.notificationservice.exception.TechnicalException;
import com.liftoff.notificationservice.service.EncoderService;
import com.liftoff.notificationservice.service.MailService;
import com.liftoff.notificationservice.service.UserActivationService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class UserActivationServiceImpl implements UserActivationService {

    private final MailService mailService;
    private final EncoderService encoderService;

    @Override
    public void sendActivationLink(ActivationUserData activationUserData) {

        String encodedEmail = activationUserData.getEncodedEmail();
        String email = encoderService.decodeBase64(encodedEmail);
        String token = activationUserData.getToken();

        String activationPath = ActivationConst.ACTIVATION_PATH;
        String query = String.format("token=%s&email=%s", token, email);

        URI activationURI = UriComponentsBuilder.fromPath(activationPath)
                .query(query)
                .build()
                .toUri();

        String subject = ActivationConst.ACTIVATION_SUBJECT_TEMPLATE;
        String message = ActivationConst.ACTIVATION_BODY_HEADER_TEMPLATE +
                ActivationConst.ACTIVATION_BODY_TEMPLATE +
                ActivationConst.ACTIVATION_LINK_BASE + activationURI +
                ActivationConst.ACTIVATION_BODY_FOOTER_TEMPLATE;

        try {
            mailService.sendEmail(email, subject, message);
        } catch (MessagingException e) {
            throw new TechnicalException("An error occurred while sending the email",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}