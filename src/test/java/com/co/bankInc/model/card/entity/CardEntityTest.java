package com.co.bankInc.model.card.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardEntityTest {

    private CardEntity cardEntity;

    @BeforeEach
    public void setUp() {
        cardEntity = new CardEntity();
        cardEntity.setCardId(1L);
        cardEntity.setProductId("123456");
        cardEntity.setCardNumber("1234567890123456");
        cardEntity.setCardholderName("John Doe");
        cardEntity.setExpirationDate("05/2027");
        cardEntity.setActive(true);
        cardEntity.setBlocked(false);
        cardEntity.setBalance(1000.0);
        cardEntity.setAllowsDollarTransactions(true);
    }

    @Test
    public void testGetCardId() {
        assertEquals(1L, cardEntity.getCardId());
    }

    @Test
    public void testGetProductId() {
        assertEquals("123456", cardEntity.getProductId());
    }

    @Test
    public void testGetCardNumber() {
        assertEquals("1234567890123456", cardEntity.getCardNumber());
    }

    @Test
    public void testGetCardholderName() {
        assertEquals("John Doe", cardEntity.getCardholderName());
    }

    @Test
    public void testGetExpirationDate() {
        assertEquals("05/2027", cardEntity.getExpirationDate());
    }

    @Test
    public void testIsActive() {
        assertTrue(cardEntity.isActive());
    }

    @Test
    public void testIsBlocked() {
        assertFalse(cardEntity.isBlocked());
    }

    @Test
    public void testGetBalance() {
        assertEquals(1000.0, cardEntity.getBalance());
    }

    @Test
    public void testAllowsDollarTransactions() {
        assertTrue(cardEntity.isAllowsDollarTransactions());
    }

    @Test
    public void testSetCardId() {
        cardEntity.setCardId(2L);
        assertEquals(2L, cardEntity.getCardId());
    }

    @Test
    public void testSetProductId() {
        cardEntity.setProductId("654321");
        assertEquals("654321", cardEntity.getProductId());
    }

    @Test
    public void testSetCardNumber() {
        cardEntity.setCardNumber("6543210987654321");
        assertEquals("6543210987654321", cardEntity.getCardNumber());
    }

    @Test
    public void testSetCardholderName() {
        cardEntity.setCardholderName("Jane Doe");
        assertEquals("Jane Doe", cardEntity.getCardholderName());
    }

    @Test
    public void testSetExpirationDate() {
        cardEntity.setExpirationDate("12/2030");
        assertEquals("12/2030", cardEntity.getExpirationDate());
    }

    @Test
    public void testSetActive() {
        cardEntity.setActive(false);
        assertFalse(cardEntity.isActive());
    }

    @Test
    public void testSetBlocked() {
        cardEntity.setBlocked(true);
        assertTrue(cardEntity.isBlocked());
    }

    @Test
    public void testSetBalance() {
        cardEntity.setBalance(500.0);
        assertEquals(500.0, cardEntity.getBalance());
    }

    @Test
    public void testSetAllowsDollarTransactions() {
        cardEntity.setAllowsDollarTransactions(false);
        assertFalse(cardEntity.isAllowsDollarTransactions());
    }
}