package com.co.bankInc.mapper.transaction;

import com.co.bankInc.model.card.entity.CardEntity;
import com.co.bankInc.model.transaction.dto.TransactionDTO;
import com.co.bankInc.model.transaction.entity.TransactionEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TransactionMapper {

    public TransactionEntity mapperTransactionDtoToEntity(Double price, CardEntity cardEntity) {

        TransactionEntity transactionEntity = new TransactionEntity();

        transactionEntity.setCard(cardEntity);
        transactionEntity.setAmount(price);
        transactionEntity.setTransactionDate(LocalDateTime.now());
        transactionEntity.setAnulated(Boolean.FALSE);

        return transactionEntity;
    }

    public TransactionDTO mapperTransactionEntityToTransactionDTO(TransactionEntity transactionEntity){

        return TransactionDTO.builder()
                .idTransaction(transactionEntity.getId())
                .cardNumber(transactionEntity.getCard().getCardNumber())
                .price(transactionEntity.getAmount())
                .build();
    }
}
