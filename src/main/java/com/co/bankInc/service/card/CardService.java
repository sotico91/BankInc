package com.co.bankInc.service.card;

import com.co.bankInc.model.card.dto.CardDTO;
import com.co.bankInc.model.card.entity.CardEntity;

public interface CardService {

    CardDTO generateCardNumber(String productId, String cardHolderName);
    void activateCard (String cardNumber);
    void blockCard(String cardNumber);
    void rechargeBalance(String cardNumber, Double balance);
    Double getBalance(String cardNumber);
    CardEntity getCardNumber(String cardNumber);
    void updateCardBalance(String cardNumber, double newBalance);
}
