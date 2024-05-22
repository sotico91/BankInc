package com.co.bankInc.model.card.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageCardNotFoundDTOTest {

    private MessageCardNotFoundDTO messageCardNotFoundDTO;

    @BeforeEach
    public void setUp() {
        messageCardNotFoundDTO = new MessageCardNotFoundDTO();
        messageCardNotFoundDTO.setCode(404);
        messageCardNotFoundDTO.setMessage("Card not found");
    }

    @Test
    public void testGetCode() {
        assertEquals(404, messageCardNotFoundDTO.getCode());
    }

    @Test
    public void testGetMessage() {
        assertEquals("Card not found", messageCardNotFoundDTO.getMessage());
    }

    @Test
    public void testSetCode() {
        messageCardNotFoundDTO.setCode(500);
        assertEquals(500, messageCardNotFoundDTO.getCode());
    }

    @Test
    public void testSetMessage() {
        messageCardNotFoundDTO.setMessage("Internal server error");
        assertEquals("Internal server error", messageCardNotFoundDTO.getMessage());
    }
}