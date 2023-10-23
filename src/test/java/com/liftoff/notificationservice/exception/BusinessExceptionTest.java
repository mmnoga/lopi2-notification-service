package com.liftoff.notificationservice.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class BusinessExceptionTest {

    @Test
    void shouldReturnBusinessExceptionWithDefaultStatus() {
        // given
        String message = "Test message";

        // when
        BusinessException exception = new BusinessException(message);

        // then
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        assertEquals(message, exception.getMessage());
    }

    @Test
    void shouldReturnBusinessExceptionWithCustomStatus() {
        // given
        String message = "Test message";
        HttpStatus customStatus = HttpStatus.BAD_REQUEST;

        // when
        BusinessException exception = new BusinessException(message, customStatus);

        // then
        assertEquals(customStatus, exception.getStatus());
        assertEquals(message, exception.getMessage());
    }

}