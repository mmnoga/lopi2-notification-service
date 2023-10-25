package com.liftoff.notificationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ActivationUserData {

    private String encodedUsername;
    private String tokenValue;

}