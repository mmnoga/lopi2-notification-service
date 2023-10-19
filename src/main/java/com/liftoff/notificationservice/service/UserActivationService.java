package com.liftoff.notificationservice.service;

import com.liftoff.notificationservice.dto.ActivationUserData;
import org.springframework.stereotype.Service;

@Service
public interface UserActivationService {

    void sendActivationLink(ActivationUserData activationUserData);

}