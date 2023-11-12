package com.liftoff.notificationservice.service;

import com.liftoff.notificationservice.dto.OrderSummaryData;

public interface OrderSummaryService {

    void sendOrderSummary(OrderSummaryData orderSummeryData);

}