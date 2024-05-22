package com.co.bankInc.service.impl;

import com.co.bankInc.model.dto.TransactionDTO;
import com.co.bankInc.service.TransactionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    private static final Logger logger = LogManager.getLogger(TransactionServiceImpl.class);

    @Override
    public TransactionDTO makePurchase(String cardNumber, Double price) {
        return null;
    }

    @Override
    public TransactionDTO getTransaction(Long Id) {
        return null;
    }

    @Override
    public void annulTransaction(String cardNumber, String idTransaction) {
        
    }


}
