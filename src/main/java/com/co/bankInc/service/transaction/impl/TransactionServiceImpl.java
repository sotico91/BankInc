package com.co.bankInc.service.transaction.impl;

import com.co.bankInc.exceptions.impl.ResourceBadRequestException;
import com.co.bankInc.exceptions.impl.ResourceNotFoundException;
import com.co.bankInc.mapper.transaction.TransactionMapper;
import com.co.bankInc.model.transaction.dto.TransactionDTO;
import com.co.bankInc.model.card.entity.CardEntity;
import com.co.bankInc.model.transaction.entity.TransactionEntity;
import com.co.bankInc.repository.transaction.TransactionRepository;
import com.co.bankInc.service.card.CardService;
import com.co.bankInc.service.transaction.TransactionService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private static final Logger logger = LogManager.getLogger(TransactionServiceImpl.class);

    private TransactionRepository transactionRepository;
    private CardService cardService;
    private TransactionMapper transactionMapper;

    @Override
    public TransactionDTO makePurchase(String cardNumber, Double price) {

        logger.info("Start makePurchase method {}",cardNumber);

        CardEntity cardEntity = cardService.getCardNumber(cardNumber);

        validateCard(cardEntity);
        validateFunds(cardEntity, price);

        cardService.updateCardBalance(cardNumber, cardEntity.getBalance() - price);

        TransactionEntity transactionEntity = transactionMapper.mapperTransactionDtoToEntity(price,cardEntity);
        TransactionEntity transactionEntityOut = transactionRepository.save(transactionEntity);

        logger.info("End makePurchase method");

        return transactionMapper.mapperTransactionEntityToTransactionDTO(transactionEntityOut);

    }

    @Override
    public TransactionDTO getTransaction(Long idTransaction) {

        logger.info("Start getTransaction method {}",idTransaction);

        TransactionEntity transactionEntity = transactionRepository.findById(idTransaction)
                .orElseThrow(() -> new ResourceNotFoundException("The transaction number: "+idTransaction +" Doesn't exist"));

        logger.info("End getTransaction method");

        return transactionMapper.mapperTransactionEntityToTransactionDTO(transactionEntity);
    }

    @Override
    public void annulTransaction(String cardNumber, Long idTransaction) {

        logger.info("Start annulTransaction method {}",idTransaction);

        TransactionEntity transactionEntity = transactionRepository.findById(idTransaction)
                .orElseThrow(() -> new ResourceNotFoundException("The transaction number: "+idTransaction +" Doesn't exist"));

        isTransactionisValid(transactionEntity);

        if (isTransactionOlderThan24Hours(transactionEntity.getTransactionDate())){
            throw new ResourceBadRequestException("The transaction was not possible anulated because transaction date greater than 24 hours");
        }

        transactionRepository.updateTransactionStatus(cardNumber, idTransaction);
        CardEntity cardEntity = cardService.getCardNumber(cardNumber);
        cardService.updateCardBalance(cardNumber, transactionEntity.getAmount() + cardEntity.getBalance());

        logger.info("End annulTransaction method {}",idTransaction);
    }

    private void validateCard(CardEntity cardEntity) {

        logger.info("Start validateCard method");

        if (!cardEntity.isActive() || cardEntity.isBlocked()) {
            throw new ResourceBadRequestException("The card number: " + cardEntity.getCardNumber() + " is blocked or inactive");
        }
    }

    private void validateFunds(CardEntity cardEntity, Double price) {

        logger.info("Start validateFunds method");

        if (price > cardEntity.getBalance()) {
            throw new ResourceBadRequestException("You do not have sufficient funds for this transaction");
        }
    }

    private void isTransactionisValid(TransactionEntity transactionEntity) {

        logger.info("Start isTransactionisValid method");

        if (transactionEntity.isAnulated()) {
            throw new ResourceBadRequestException("The transaction is found reversed");
        }
    }

    private boolean isTransactionOlderThan24Hours(LocalDateTime transactionDate) {
        LocalDateTime now = LocalDateTime.now();
        long hoursDifference = ChronoUnit.HOURS.between(transactionDate, now);
        return hoursDifference > 24;
    }

}