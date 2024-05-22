package com.co.bankInc.model.generalMessage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MessageDTOTest {

    private MessageDTO messageDTO;

    @BeforeEach
    public void setUp() {
        messageDTO = new MessageDTO();
        messageDTO.setCode(200);
        messageDTO.setMessage("Operation completed successfully");
    }

    @Test
    public void testGetCode() {
        assertEquals(200, messageDTO.getCode());
    }

    @Test
    public void testGetMessage() {
        assertEquals("Operation completed successfully", messageDTO.getMessage());
    }

    @Test
    public void testSetCode() {
        messageDTO.setCode(201);
        assertEquals(201, messageDTO.getCode());
    }

    @Test
    public void testSetMessage() {
        messageDTO.setMessage("Created successfully");
        assertEquals("Created successfully", messageDTO.getMessage());
    }
}