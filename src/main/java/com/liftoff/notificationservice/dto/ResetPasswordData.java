package com.liftoff.notificationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ResetPasswordData {

    private String encodedUsername;
    private String tokenValue;

}