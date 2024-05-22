package com.co.bankInc.model.card.dto;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class CardDTOTest {

    private CardDTO cardDTO;

    @BeforeEach
    public void setUp() {
        cardDTO = CardDTO.builder()
                .productId("123456")
                .cardNumber("1234567890123456")
                .cardholderName("Nestor Alvarez")
                .expirationDate("05/2027")
                .balance(1000.0)
                .build();
    }

    @Test
    public void testGetProductId() {
        assertEquals("123456", cardDTO.getProductId());
    }

    @Test
    public void testGetCardNumber() {
        assertEquals("1234567890123456", cardDTO.getCardNumber());
    }

    @Test
    public void testGetCardholderName() {
        assertEquals("Nestor Alvarez", cardDTO.getCardholderName());
    }

    @Test
    public void testGetExpirationDate() {
        assertEquals("05/2027", cardDTO.getExpirationDate());
    }

    @Test
    public void testGetBalance() {
        assertEquals(1000.0, cardDTO.getBalance());
    }

    @Test
    public void testSetProductId() {
        cardDTO.setProductId("654321");
        assertEquals("654321", cardDTO.getProductId());
    }

    @Test
    public void testSetCardNumber() {
        cardDTO.setCardNumber("6543210987654321");
        assertEquals("6543210987654321", cardDTO.getCardNumber());
    }

    @Test
    public void testSetCardholderName() {
        cardDTO.setCardholderName("Jane Doe");
        assertEquals("Jane Doe", cardDTO.getCardholderName());
    }

    @Test
    public void testSetExpirationDate() {
        cardDTO.setExpirationDate("12/2030");
        assertEquals("12/2030", cardDTO.getExpirationDate());
    }

    @Test
    public void testSetBalance() {
        cardDTO.setBalance(500.0);
        assertEquals(500.0, cardDTO.getBalance());
    }
}