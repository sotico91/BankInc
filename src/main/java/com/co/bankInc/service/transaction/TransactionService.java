package com.co.bankInc.service.transaction;

import com.co.bankInc.model.transaction.dto.TransactionDTO;

public interface TransactionService {
    TransactionDTO makePurchase(String cardNumber, Double price);
    TransactionDTO getTransaction(Long Id);
    void annulTransaction(String cardNumber, Long idTransaction);
}