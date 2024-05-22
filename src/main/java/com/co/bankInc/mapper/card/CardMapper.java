package com.co.bankInc.mapper.card;

import com.co.bankInc.model.card.dto.CardDTO;
import com.co.bankInc.model.card.entity.CardEntity;
import org.springframework.stereotype.Component;

@Component
public class CardMapper {

    public CardEntity mapperCardDtoTOEntity(CardDTO cardDTO){

        CardEntity cardEntity = new CardEntity();
        cardEntity.setProductId(cardDTO.getProductId());
        cardEntity.setCardNumber(cardDTO.getCardNumber());
        cardEntity.setCardholderName(cardDTO.getCardholderName());
        cardEntity.setExpirationDate(cardDTO.getExpirationDate());
        cardEntity.setActive(Boolean.FALSE);
        cardEntity.setBalance(0.0);
        cardEntity.setBlocked(Boolean.FALSE);

        return cardEntity;

    }

    public CardDTO mapperCardEntityToDto(CardEntity cardEntity){

        return CardDTO.builder()
                .productId(cardEntity.getProductId())
                .cardNumber(cardEntity.getCardNumber())
                .cardholderName(cardEntity.getCardholderName())
                .expirationDate(cardEntity.getExpirationDate())
                .balance(cardEntity.getBalance())
                .build();
    }

}
