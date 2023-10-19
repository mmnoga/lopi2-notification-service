package com.liftoff.notificationservice.service;

import jakarta.mail.MessagingException;

public interface MailService {

    void sendEmail(String to, String subject, String message) throws MessagingException;

}