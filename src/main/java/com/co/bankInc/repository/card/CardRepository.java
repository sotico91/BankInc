package com.co.bankInc.repository.card;

import com.co.bankInc.model.card.entity.CardEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, String> {

    Optional<CardEntity> findByCardNumber(String cardNumber);

    @Modifying
    @Transactional
    @Query("UPDATE CardEntity c SET c.balance = :newBalance WHERE c.cardNumber = :cardNumber")
    void updateBalanceByCardNumber(String cardNumber, double newBalance);

}
