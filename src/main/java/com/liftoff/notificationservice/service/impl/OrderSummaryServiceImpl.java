package com.liftoff.notificationservice.service.impl;

import com.liftoff.notificationservice.config.constants.OrderConst;
import com.liftoff.notificationservice.dto.OrderSummaryData;
import com.liftoff.notificationservice.exception.BusinessException;
import com.liftoff.notificationservice.exception.TechnicalException;
import com.liftoff.notificationservice.service.MailService;
import com.liftoff.notificationservice.service.OrderSummaryService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class OrderSummaryServiceImpl implements OrderSummaryService {

    private final TemplateEngine templateEngine;
    private final MailService mailService;

    @Override
    public void sendOrderSummary(OrderSummaryData orderSummaryData) {

        if (orderSummaryData == null) {
            throw new BusinessException("orderSummeryData cannot be null", HttpStatus.BAD_REQUEST);
        }

        String email = orderSummaryData.getEmail();
        String subject = OrderConst.ORDER_SUBJECT_TEMPLATE;

        Context context = getContext(orderSummaryData);
        String message = templateEngine
                .process("email_order_summary", context);

        try {
            mailService.sendEmail(email, subject, message);
        } catch (MessagingException e) {
            throw new TechnicalException("An error occurred while sending the email",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Context getContext(OrderSummaryData summaryData) {
        Context context = new Context();
        context.setVariable("orderNumber", summaryData.getOrderNumber());
        context.setVariable("productList", summaryData.getProductList());
        context.setVariable("customerName", summaryData.getCustomerName());
        context.setVariable("totalProductsPrice", summaryData.getTotalProductsPrice());
        context.setVariable("deliveryMethod", summaryData.getDeliveryMethod());
        context.setVariable("paymentMethod", summaryData.getPaymentMethod());
        context.setVariable("totalPrice", summaryData.getTotalPrice());
        context.setVariable("billingStreetAndNumber", summaryData.getBillingStreetAndNumber());
        context.setVariable("billingPostalCodeAndCity", summaryData.getBillingPostalCodeAndCity());
        context.setVariable("phoneNumber", summaryData.getPhoneNumber());
        context.setVariable("customerEmail", summaryData.getCustomerEmail());
        context.setVariable("deliveryStreetAndNumber", summaryData.getDeliveryStreetAndNumber());
        context.setVariable("deliveryPostalCodeAndCity", summaryData.getDeliveryPostalCodeAndCity());

        return context;
    }

}