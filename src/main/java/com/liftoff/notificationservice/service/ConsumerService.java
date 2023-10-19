package com.liftoff.notificationservice.service;

import com.liftoff.notificationservice.dto.ActivationUserData;

public interface ConsumerService {

    void consumeActivationUserData(ActivationUserData activationUserData);

}