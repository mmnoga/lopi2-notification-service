package com.liftoff.notificationservice.service.impl;

import com.liftoff.notificationservice.dto.ActivationUserData;
import com.liftoff.notificationservice.dto.OrderSummaryData;
import com.liftoff.notificationservice.dto.ResetPasswordData;
import com.liftoff.notificationservice.service.ConsumerService;
import com.liftoff.notificationservice.service.OrderSummaryService;
import com.liftoff.notificationservice.service.ResetUserPasswordService;
import com.liftoff.notificationservice.service.UserActivationService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {

    private final UserActivationService userActivationService;
    private final ResetUserPasswordService resetUserPasswordService;
    private final OrderSummaryService orderSummaryService;

    @Override
    @RabbitListener(queues = {"${user.register.queue.name}"})
    public void consumeActivationUserData(ActivationUserData activationUserData) {
        userActivationService
                .sendActivationLink(activationUserData);
    }

    @Override
    @RabbitListener(queues = {"${user.resetPassword.queue.name}"})
    public void consumeResetUserPasswordData(ResetPasswordData resetPasswordData) {
        resetUserPasswordService
                .sendResetPasswordLink(resetPasswordData);
    }

    @Override
    @RabbitListener(queues = {"${order.summary.queue.name}"})
    public void consumeOrderSummaryData(OrderSummaryData orderSummaryData) {
        orderSummaryService
                .sendOrderSummary(orderSummaryData);
    }

}