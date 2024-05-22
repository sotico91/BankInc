package com.co.bankInc.mapper.card;

import com.co.bankInc.model.card.dto.CardDTO;
import com.co.bankInc.model.card.entity.CardEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class CardMapperTest {

    private final CardMapper cardMapper = new CardMapper();

    @Test
    void testMapperCardDtoTOEntity() {

        CardDTO cardDTO = CardDTO.builder()
                .productId("123456")
                .cardNumber("1234567890123456")
                .cardholderName("Edgar Velasco")
                .expirationDate("12/25")
                .build();

        CardEntity cardEntity = cardMapper.mapperCardDtoTOEntity(cardDTO);

        assertEquals(cardDTO.getProductId(), cardEntity.getProductId());
        assertEquals(cardDTO.getCardNumber(), cardEntity.getCardNumber());
        assertEquals(cardDTO.getCardholderName(), cardEntity.getCardholderName());
        assertEquals(cardDTO.getExpirationDate(), cardEntity.getExpirationDate());
        assertEquals(0.0, cardEntity.getBalance());
        assertFalse(cardEntity.isActive());
        assertFalse(cardEntity.isBlocked());
    }

    @Test
    void testMapperCardEntityToDto() {

        CardEntity cardEntity = new CardEntity();
        cardEntity.setProductId("123456");
        cardEntity.setCardNumber("1234567890123456");
        cardEntity.setCardholderName("Edgar Velasco");
        cardEntity.setExpirationDate("12/25");
        cardEntity.setBalance(100.0);

        CardDTO cardDTO = cardMapper.mapperCardEntityToDto(cardEntity);

        assertEquals(cardEntity.getProductId(), cardDTO.getProductId());
        assertEquals(cardEntity.getCardNumber(), cardDTO.getCardNumber());
        assertEquals(cardEntity.getCardholderName(), cardDTO.getCardholderName());
        assertEquals(cardEntity.getExpirationDate(), cardDTO.getExpirationDate());
        assertEquals(cardEntity.getBalance(), cardDTO.getBalance());
    }
}