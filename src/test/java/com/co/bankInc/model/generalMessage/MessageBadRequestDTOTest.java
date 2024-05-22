package com.co.bankInc.model.generalMessage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MessageBadRequestDTOTest {

    private MessageBadRequestDTO messageBadRequestDTO;

    @BeforeEach
    public void setUp() {
        messageBadRequestDTO = new MessageBadRequestDTO();
        messageBadRequestDTO.setCode(400);
        messageBadRequestDTO.setMessage("Bad Request Op");
    }

    @Test
    public void testGetCode() {
        assertEquals(400, messageBadRequestDTO.getCode());
    }

    @Test
    public void testGetMessage() {
        assertEquals("Bad Request Op", messageBadRequestDTO.getMessage());
    }

    @Test
    public void testSetCode() {
        messageBadRequestDTO.setCode(401);
        assertEquals(401, messageBadRequestDTO.getCode());
    }

    @Test
    public void testSetMessage() {
        messageBadRequestDTO.setMessage("Unauthorized Access");
        assertEquals("Unauthorized Access", messageBadRequestDTO.getMessage());
    }
}