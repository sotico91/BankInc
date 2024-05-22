package com.co.bankInc.model.transaction.entity;

import com.co.bankInc.model.card.entity.CardEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TransactionEntityTest {

    private TransactionEntity transactionEntity;
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

        transactionEntity = new TransactionEntity();
        transactionEntity.setId(1L);
        transactionEntity.setAmount(100.0);
        transactionEntity.setTransactionDate(LocalDateTime.now());
        transactionEntity.setAnulated(false);
        transactionEntity.setCard(cardEntity);
    }

    @Test
    public void testGetId() {
        assertEquals(1L, transactionEntity.getId());
    }

    @Test
    public void testGetAmount() {
        assertEquals(100.0, transactionEntity.getAmount());
    }

    @Test
    public void testGetTransactionDate() {
        assertEquals(LocalDateTime.now().getYear(), transactionEntity.getTransactionDate().getYear());
        assertEquals(LocalDateTime.now().getMonth(), transactionEntity.getTransactionDate().getMonth());
        assertEquals(LocalDateTime.now().getDayOfMonth(), transactionEntity.getTransactionDate().getDayOfMonth());
    }

    @Test
    public void testIsAnulated() {
        assertFalse(transactionEntity.isAnulated());
    }

    @Test
    public void testGetCard() {
        assertEquals(cardEntity, transactionEntity.getCard());
    }

    @Test
    public void testSetId() {
        transactionEntity.setId(2L);
        assertEquals(2L, transactionEntity.getId());
    }

    @Test
    public void testSetAmount() {
        transactionEntity.setAmount(200.0);
        assertEquals(200.0, transactionEntity.getAmount());
    }

    @Test
    public void testSetTransactionDate() {
        LocalDateTime newDate = LocalDateTime.of(2024, 1, 1, 0, 0);
        transactionEntity.setTransactionDate(newDate);
        assertEquals(newDate, transactionEntity.getTransactionDate());
    }

    @Test
    public void testSetAnulated() {
        transactionEntity.setAnulated(true);
        assertTrue(transactionEntity.isAnulated());
    }

    @Test
    public void testSetCard() {
        CardEntity newCard = new CardEntity();
        newCard.setCardId(2L);
        newCard.setProductId("654321");
        newCard.setCardNumber("6543210987654321");
        newCard.setCardholderName("Jane Doe");
        newCard.setExpirationDate("12/2030");
        newCard.setActive(true);
        newCard.setBlocked(false);
        newCard.setBalance(2000.0);
        newCard.setAllowsDollarTransactions(true);

        transactionEntity.setCard(newCard);
        assertEquals(newCard, transactionEntity.getCard());
    }
}