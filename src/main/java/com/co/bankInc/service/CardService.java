package com.co.bankInc.service;

import com.co.bankInc.model.dto.CardDTO;

public interface CardService {

    CardDTO generateCardNumber(String productId, String cardHolderName);
    void activateCard (String cardNumber);
    void blockCard(String cardNumber);
    void rechargeBalance(String cardNumber, Double balance);
    Double getBalance(String cardNumber);
}
