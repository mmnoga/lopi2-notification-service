package com.liftoff.notificationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class OrderSummaryData {

    String orderNumber;
    List<ProductItemData> productList;
    Double totalProductsPrice;
    String deliveryMethod;
    String paymentMethod;
    Double totalPrice;
    String customerName;
    String billingStreetAndNumber;
    String billingPostalCodeAndCity;
    String phoneNumber;
    String customerEmail;
    String deliveryStreetAndNumber;
    String deliveryPostalCodeAndCity;
    String email;

}