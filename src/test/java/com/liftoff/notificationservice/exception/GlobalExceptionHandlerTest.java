package com.liftoff.notificationservice.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler exceptionHandler;

    @BeforeEach
    public void setUp() {
        exceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void shouldHandleTechnicalException() {
        // given
        TechnicalException technicalException =
                new TechnicalException("Test error", HttpStatus.INTERNAL_SERVER_ERROR);

        // when
        ResponseEntity<Map<String, String>> response =
                exceptionHandler.handleTechnicalException(technicalException);

        // then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Map<String, String> responseBody = response.getBody();
        assertEquals("Test error", responseBody.get("message"));
    }

}