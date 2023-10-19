package com.liftoff.notificationservice.service;

public interface EncoderService {

    String encodeToBase64(String text);
    String decodeBase64(String encodedText);

}