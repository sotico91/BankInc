package com.co.bankInc.service;

import com.co.bankInc.model.dto.TransactionDTO;

public interface TransactionService {
    TransactionDTO makePurchase(String cardNumber, Double price);
    TransactionDTO getTransaction(Long Id);
    void annulTransaction(String cardNumber, String idTransaction);
}