package com.co.bankInc.exceptions.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResourceBadRequestExceptionTest {

    @Test
    void testConstructor() {
        String message = "Bad request";

        ResourceBadRequestException exception = new ResourceBadRequestException(message);

        assertEquals(message, exception.getMessage());
    }
}