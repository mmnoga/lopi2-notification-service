package com.liftoff.notificationservice.service;

import jakarta.mail.MessagingException;

public interface MailService {

    /**
     * Sends an email with the specified content.
     *
     * @param to      The recipient's email address.
     * @param subject The subject of the email.
     * @param message The message content of the email.
     * @throws MessagingException If there was an issue with sending the email.
     */
    void sendEmail(String to, String subject, String message) throws MessagingException;

}