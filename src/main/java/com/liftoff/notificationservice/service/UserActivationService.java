package com.liftoff.notificationservice.service;

import com.liftoff.notificationservice.dto.ActivationUserData;
import com.liftoff.notificationservice.exception.TechnicalException;
import org.springframework.stereotype.Service;

@Service
public interface UserActivationService {

    /**
     * Sends an activation link email to the provided user with the activation data.
     *
     * @param activationUserData The activation data including encoded email and token.
     * @throws TechnicalException If an error occurs while sending the email, a custom exception is thrown.
     *                            The exception message is "An error occurred while sending the email," and
     *                            the HTTP status code is set to INTERNAL_SERVER_ERROR (500).
     */
    void sendActivationLink(ActivationUserData activationUserData);

}