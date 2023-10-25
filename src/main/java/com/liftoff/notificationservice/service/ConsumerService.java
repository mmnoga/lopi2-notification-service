package com.liftoff.notificationservice.service;

import com.liftoff.notificationservice.dto.ActivationUserData;

public interface ConsumerService {

    /**
     * Listens for and consumes messages from a RabbitMQ queue containing activation user data.
     *
     * @param activationUserData The activation user data received from the RabbitMQ queue.
     */
    void consumeActivationUserData(ActivationUserData activationUserData);

}