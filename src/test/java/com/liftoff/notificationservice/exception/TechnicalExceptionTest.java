package com.liftoff.notificationservice.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TechnicalExceptionTest {

    @Test
    void shouldReturnTechnicalExceptionWithDefaultStatus() {
        // given
        String message = "Test message";

        // when
        TechnicalException exception = new TechnicalException(message);

        // then
        assertEquals(HttpStatus.FOUND, exception.getStatus());
        assertEquals(message, exception.getMessage());
    }

    @Test
    void shouldReturnTechnicalExceptionWithCustomStatus() {
        // given
        String message = "Test message";
        HttpStatus customStatus = HttpStatus.BAD_REQUEST;

        // when
        TechnicalException exception = new TechnicalException(message, customStatus);

        // then
        assertEquals(customStatus, exception.getStatus());
        assertEquals(message, exception.getMessage());
    }

}