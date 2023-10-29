package com.liftoff.notificationservice.service;

import com.liftoff.notificationservice.dto.ResetPasswordData;
import com.liftoff.notificationservice.exception.BusinessException;
import com.liftoff.notificationservice.exception.TechnicalException;

public interface ResetUserPasswordService {

    /**
     * Sends a reset password link to the specified email address.
     *
     * @param resetPasswordData The data containing the encoded email and reset token.
     * @throws BusinessException   If the resetPasswordData is null or invalid, resulting in a bad request.
     * @throws TechnicalException If an error occurs while sending the email, resulting in an internal server error.
     */
    void sendResetPasswordLink(ResetPasswordData resetPasswordData);

}