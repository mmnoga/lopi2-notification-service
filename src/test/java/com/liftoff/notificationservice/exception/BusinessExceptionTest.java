package com.liftoff.notificationservice.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BusinessExceptionTest {

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

    @Test
    void shouldHandleBusinessException() {
        // given
        BusinessException businessException =
                new BusinessException("Test business error", HttpStatus.BAD_REQUEST);

        // when
        ResponseEntity<Map<String, String>> response =
                exceptionHandler.handleBusinessException(businessException);

        // then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Map<String, String> responseBody = response.getBody();
        assertEquals("Test business error", responseBody.get("message"));
    }

    @Test
    void shouldHandleBusinessExceptionWithoutStatus() {
        // given
        BusinessException businessException =
                new BusinessException("Test business error without status");

        // when
        ResponseEntity<Map<String, String>> response =
                exceptionHandler.handleBusinessException(businessException);

        // then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Map<String, String> responseBody = response.getBody();
        assertEquals("Test business error without status", responseBody.get("message"));
    }

}