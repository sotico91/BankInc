package com.co.bankInc.model.transaction.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionDTOTest {

    private TransactionDTO transactionDTO;

    @BeforeEach
    public void setUp() {
        transactionDTO = TransactionDTO.builder()
                .idTransaction(1L)
                .cardNumber("1234567890123456")
                .price(100.0)
                .build();
    }

    @Test
    public void testGetIdTransaction() {
        assertEquals(1L, transactionDTO.getIdTransaction());
    }

    @Test
    public void testGetCardNumber() {
        assertEquals("1234567890123456", transactionDTO.getCardNumber());
    }

    @Test
    public void testGetPrice() {
        assertEquals(100.0, transactionDTO.getPrice());
    }

    @Test
    public void testSetIdTransaction() {
        transactionDTO.setIdTransaction(2L);
        assertEquals(2L, transactionDTO.getIdTransaction());
    }

    @Test
    public void testSetCardNumber() {
        transactionDTO.setCardNumber("6543210987654321");
        assertEquals("6543210987654321", transactionDTO.getCardNumber());
    }

    @Test
    public void testSetPrice() {
        transactionDTO.setPrice(200.0);
        assertEquals(200.0, transactionDTO.getPrice());
    }
}