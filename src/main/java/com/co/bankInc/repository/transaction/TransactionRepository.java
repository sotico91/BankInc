package com.co.bankInc.repository.transaction;

import com.co.bankInc.model.transaction.entity.TransactionEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE TransactionEntity c SET c.isAnulated = true WHERE c.id = :idTransaction and c.card.cardNumber= :cardNumber")
    void updateTransactionStatus(String cardNumber, Long idTransaction);

}
