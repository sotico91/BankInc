package com.co.bankInc.mapper.transaction;

import com.co.bankInc.model.card.entity.CardEntity;
import com.co.bankInc.model.transaction.dto.TransactionDTO;
import com.co.bankInc.model.transaction.entity.TransactionEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class TransactionMapperTest {

    private final TransactionMapper transactionMapper = new TransactionMapper();

    @Test
    void testMapperTransactionDtoToEntity() {
        Double price = 100.0;
        CardEntity cardEntity = new CardEntity();

        TransactionEntity transactionEntity = transactionMapper.mapperTransactionDtoToEntity(price, cardEntity);

        assertEquals(cardEntity, transactionEntity.getCard());
        assertEquals(price, transactionEntity.getAmount());
        assertEquals(LocalDateTime.now().getDayOfYear(), transactionEntity.getTransactionDate().getDayOfYear());
        assertFalse(transactionEntity.isAnulated());
    }

    @Test
    void testMapperTransactionEntityToTransactionDTO() {
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setId(1L);
        CardEntity cardEntity = new CardEntity();
        cardEntity.setCardNumber("1234567890123456");
        transactionEntity.setCard(cardEntity);
        transactionEntity.setAmount(100.0);

        TransactionDTO transactionDTO = transactionMapper.mapperTransactionEntityToTransactionDTO(transactionEntity);

        assertEquals(transactionEntity.getId(), transactionDTO.getIdTransaction());
        assertEquals(transactionEntity.getCard().getCardNumber(), transactionDTO.getCardNumber());
        assertEquals(transactionEntity.getAmount(), transactionDTO.getPrice());
    }
}