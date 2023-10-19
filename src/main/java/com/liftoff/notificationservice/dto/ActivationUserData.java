package com.liftoff.notificationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ActivationUserData {

    private String token;
    private String encodedEmail;

}