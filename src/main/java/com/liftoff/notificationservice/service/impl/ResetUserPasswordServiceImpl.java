package com.liftoff.notificationservice.service.impl;

import com.liftoff.notificationservice.config.constants.ResetPasswordConst;
import com.liftoff.notificationservice.dto.ResetPasswordData;
import com.liftoff.notificationservice.exception.BusinessException;
import com.liftoff.notificationservice.exception.TechnicalException;
import com.liftoff.notificationservice.service.EncoderService;
import com.liftoff.notificationservice.service.MailService;
import com.liftoff.notificationservice.service.ResetUserPasswordService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class ResetUserPasswordServiceImpl implements ResetUserPasswordService {

    private final MailService mailService;
    private final EncoderService encoderService;
    private final String resetLinkBase;
    private final String resetPath;

    public ResetUserPasswordServiceImpl(MailService mailService,
                                        EncoderService encoderService,
                                        @Value("${user.resetPassword.link-base}") String resetLinkBase,
                                        @Value("${user.resetPassword.path}") String resetPath) {
        this.mailService = mailService;
        this.encoderService = encoderService;
        this.resetLinkBase = resetLinkBase;
        this.resetPath = resetPath;
    }

    @Override
    public void sendResetPasswordLink(ResetPasswordData resetPasswordData) {

        if (resetPasswordData == null) {
            throw new BusinessException("resetPasswordData cannot be null", HttpStatus.BAD_REQUEST);
        }

        String encodedEmail = resetPasswordData.getEncodedUsername();
        String token = resetPasswordData.getTokenValue();
        String email = encoderService.decodeBase64(encodedEmail);

        String query = String.format("token=%s&email=%s", token, encodedEmail);

        URI resetnURI = UriComponentsBuilder.fromPath(resetPath)
                .query(query)
                .build()
                .toUri();

        String subject = ResetPasswordConst.SUBJECT_TEMPLATE;
        String message = ResetPasswordConst.BODY_HEADER_TEMPLATE +
                ResetPasswordConst.BODY_TEMPLATE +
                resetLinkBase + resetnURI +
                ResetPasswordConst.BODY_FOOTER_TEMPLATE;

        try {
            mailService.sendEmail(email, subject, message);
        } catch (MessagingException e) {
            throw new TechnicalException("An error occurred while sending the email",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}