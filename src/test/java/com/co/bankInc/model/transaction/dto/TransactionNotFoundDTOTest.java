package com.co.bankInc.model.transaction.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionNotFoundDTOTest {

    private TransactionNotFoundDTO transactionNotFoundDTO;

    @BeforeEach
    public void setUp() {
        transactionNotFoundDTO = new TransactionNotFoundDTO();
        transactionNotFoundDTO.setCode(404);
        transactionNotFoundDTO.setMessage("Transaction not found");
    }

    @Test
    public void testGetCode() {
        assertEquals(404, transactionNotFoundDTO.getCode());
    }

    @Test
    public void testGetMessage() {
        assertEquals("Transaction not found", transactionNotFoundDTO.getMessage());
    }

    @Test
    public void testSetCode() {
        transactionNotFoundDTO.setCode(500);
        assertEquals(500, transactionNotFoundDTO.getCode());
    }

    @Test
    public void testSetMessage() {
        transactionNotFoundDTO.setMessage("Internal server error");
        assertEquals("Internal server error", transactionNotFoundDTO.getMessage());
    }
}