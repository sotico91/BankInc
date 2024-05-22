package com.co.bankInc.repository.transaction;

import com.co.bankInc.model.card.entity.CardEntity;
import com.co.bankInc.model.transaction.entity.TransactionEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @Rollback(false)
    void testUpdateTransactionStatus() {

        CardEntity cardEntity = new CardEntity();
        cardEntity.setCardNumber("1234567890123456");
        entityManager.persistAndFlush(cardEntity);

        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setCard(cardEntity);
        transactionEntity.setAmount(100.0);
        transactionEntity.setTransactionDate(LocalDateTime.now());
        transactionEntity.setAnulated(false);
        entityManager.persistAndFlush(transactionEntity);

        transactionRepository.updateTransactionStatus("1234567890123456", transactionEntity.getId());

        TransactionEntity updatedTransactionEntity = entityManager.find(TransactionEntity.class, transactionEntity.getId());
        assertNotNull(updatedTransactionEntity);
        assertFalse(updatedTransactionEntity.isAnulated());
    }
}