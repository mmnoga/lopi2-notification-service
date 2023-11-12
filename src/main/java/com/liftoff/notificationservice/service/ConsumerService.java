package com.liftoff.notificationservice.service;

import com.liftoff.notificationservice.dto.ActivationUserData;
import com.liftoff.notificationservice.dto.OrderSummaryData;
import com.liftoff.notificationservice.dto.ResetPasswordData;

public interface ConsumerService {

    /**
     * Listens for and consumes messages from a RabbitMQ queue containing activation user data.
     *
     * @param activationUserData The activation user data received from the RabbitMQ queue.
     */
    void consumeActivationUserData(ActivationUserData activationUserData);

    /**
     * Listens for and consumes messages from a RabbitMQ queue containing activation user data.
     *
     * @param resetPasswordData The reset user password data received from the RabbitMQ queue.
     */
    void consumeResetUserPasswordData(ResetPasswordData resetPasswordData);

    /**
     * Listens for and consumes messages from a RabbitMQ queue containing order summary data.
     *
     * @param orderSummaryData The order summary data received from the RabbitMQ queue.
     */
    void consumeOrderSummaryData(OrderSummaryData orderSummaryData);

}