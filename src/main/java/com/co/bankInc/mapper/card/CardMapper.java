package com.co.bankInc.mapper.card;

import com.co.bankInc.model.card.dto.CardDTO;
import com.co.bankInc.model.card.entity.CardEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CardMapper {

    ModelMapper modelMapper = new ModelMapper();

    public CardEntity mapperCardDtoTOEntity(CardDTO cardDTO){

        return modelMapper.map(cardDTO, CardEntity.class);
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