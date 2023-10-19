package com.liftoff.notificationservice.service.impl;

import com.liftoff.notificationservice.dto.ActivationUserData;
import com.liftoff.notificationservice.service.ConsumerService;
import com.liftoff.notificationservice.service.UserActivationService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {

    private final UserActivationService userActivationService;

    @Override
    @RabbitListener(queues = {"${user.register.queue.name}"})
    public void consumeActivationUserData(ActivationUserData activationUserData) {
        userActivationService
                .sendActivationLink(activationUserData);
    }

}