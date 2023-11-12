package com.liftoff.notificationservice.dto;

import lombok.Data;

@Data
public class ProductItemData {

    String productName;
    String productCategory;
    Integer productQuantity;
    Double productPrice;
    Double productTotalPrice;

}