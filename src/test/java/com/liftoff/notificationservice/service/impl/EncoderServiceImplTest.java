package com.liftoff.notificationservice.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EncoderServiceImplTest {

    @InjectMocks
    private EncoderServiceImpl encoderService;

    @Test
    void shouldEncodeEmail() {
        // given
        String email = "test@email.com";
        String expectedEncodedEmail = "dGVzdEBlbWFpbC5jb20=";

        // when
        String encodedText = encoderService.encodeToBase64(email);

        //then
        assertEquals(expectedEncodedEmail, encodedText);
    }

    @Test
    void shouldDecodeEmail() {
        // given
        String encodedEmail = "dGVzdEBlbWFpbC5jb20=";
        String expectedDecodedEmail = "test@email.com";

        // when
        String decodedText = encoderService.decodeBase64(encodedEmail);

        // then
        assertEquals(expectedDecodedEmail, decodedText);
    }

}