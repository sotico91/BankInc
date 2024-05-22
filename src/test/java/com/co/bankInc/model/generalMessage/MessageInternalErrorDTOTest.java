package com.co.bankInc.model.generalMessage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MessageInternalErrorDTOTest {

    private MessageInternalErrorDTO messageInternalErrorDTO;

    @BeforeEach
    public void setUp() {
        messageInternalErrorDTO = new MessageInternalErrorDTO();
        messageInternalErrorDTO.setCode(500);
        messageInternalErrorDTO.setMessage("Internal Server Error");
    }

    @Test
    public void testGetCode() {
        assertEquals(500, messageInternalErrorDTO.getCode());
    }

    @Test
    public void testGetMessage() {
        assertEquals("Internal Server Error", messageInternalErrorDTO.getMessage());
    }

    @Test
    public void testSetCode() {
        messageInternalErrorDTO.setCode(501);
        assertEquals(501, messageInternalErrorDTO.getCode());
    }

    @Test
    public void testSetMessage() {
        messageInternalErrorDTO.setMessage("Not Implemented");
        assertEquals("Not Implemented", messageInternalErrorDTO.getMessage());
    }
}