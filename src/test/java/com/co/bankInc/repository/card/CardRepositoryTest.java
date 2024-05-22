package com.co.bankInc.repository.card;

import com.co.bankInc.model.card.entity.CardEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class CardRepositoryTest {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testFindByCardNumber() {

        CardEntity cardEntity = new CardEntity();
        cardEntity.setCardNumber("202020890123456");
        cardEntity.setBalance(1000.0);
        entityManager.persistAndFlush(cardEntity);

        Optional<CardEntity> optionalCardEntity = cardRepository.findByCardNumber("202020890123456");

        assertTrue(optionalCardEntity.isPresent());
        assertEquals(cardEntity.getCardNumber(), optionalCardEntity.get().getCardNumber());
        assertEquals(cardEntity.getBalance(), optionalCardEntity.get().getBalance());
    }

    @Test
    @Rollback(false)
    public void testUpdateBalanceByCardNumber() {

        CardEntity cardEntity = new CardEntity();
        cardEntity.setCardNumber("202021890123456");
        cardEntity.setBalance(1000.0);
        entityManager.persistAndFlush(cardEntity);

        cardRepository.updateBalanceByCardNumber("202021890123456", 1500.0);

        entityManager.flush();
        entityManager.clear();

        Optional<CardEntity> optionalCardEntity = cardRepository.findByCardNumber("202021890123456");
        assertTrue(optionalCardEntity.isPresent());
        assertEquals(1500.0, optionalCardEntity.get().getBalance());
    }
}