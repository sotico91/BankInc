package com.co.bankInc.service.card.impl;

import com.co.bankInc.exceptions.impl.ResourceBadRequestException;
import com.co.bankInc.exceptions.impl.ResourceNotFoundException;
import com.co.bankInc.mapper.card.CardMapper;
import com.co.bankInc.model.card.dto.CardDTO;
import com.co.bankInc.model.card.entity.CardEntity;
import com.co.bankInc.repository.card.CardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CardServiceImplTest {

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private CardServiceImpl cardService;

    @Mock
    private CardMapper mapper;

    private CardEntity cardEntity;

    private CardDTO cardDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        cardEntity = new CardEntity();
        cardEntity.setProductId("123456");
        cardEntity.setCardNumber("1234567890123456");
        cardEntity.setCardholderName("Edgar Velasco");
        cardEntity.setExpirationDate("12/25");
        cardEntity.setBalance(100.0);

        cardDTO = CardDTO.builder()
                .productId("123456")
                .cardNumber("1234567890123456")
                .cardholderName("Edgar Velasco")
                .expirationDate("12/25")
                .build();

    }

    @Test
    void testGenerateCardNumber() {

        String productId = "123456";
        String cardHolderName = "Edgar Velasco";
        when(cardRepository.saveAndFlush(any())).thenAnswer(invocation -> invocation.getArgument(0));
        when(mapper.mapperCardDtoTOEntity(any())).thenReturn(cardEntity);
        when(mapper.mapperCardEntityToDto(any())).thenReturn(cardDTO);

        CardDTO cardDTO = cardService.generateCardNumber(productId, cardHolderName);

        assertNotNull(cardDTO);
        assertEquals(productId, cardDTO.getProductId());
        assertEquals(cardHolderName, cardDTO.getCardholderName());
        assertEquals(16, cardDTO.getCardNumber().length());
    }

    @Test
    void testActivateCard() {

        String cardNumber = "1234567890123456";
        CardEntity cardEntity = new CardEntity();
        cardEntity.setCardNumber(cardNumber);
        when(cardRepository.findByCardNumber(cardNumber)).thenReturn(Optional.of(cardEntity));

        cardService.activateCard(cardNumber);

        assertTrue(cardEntity.isActive());
        assertFalse(cardEntity.isBlocked());
        verify(cardRepository, times(1)).saveAndFlush(cardEntity);
    }

    @Test
    void testBlockCard() {

        String cardNumber = "1234567890123456";
        CardEntity cardEntity = new CardEntity();
        cardEntity.setCardNumber(cardNumber);
        when(cardRepository.findByCardNumber(cardNumber)).thenReturn(Optional.of(cardEntity));

        cardService.blockCard(cardNumber);

        assertFalse(cardEntity.isActive());
        assertTrue(cardEntity.isBlocked());
        verify(cardRepository, times(1)).saveAndFlush(cardEntity);
    }


    @Test
    void testRechargeBalance() {

        String cardNumber = "1122337890123456";
        double balance = 100.0;
        CardEntity cardEntity = new CardEntity();
        cardEntity.setCardNumber(cardNumber);
        cardEntity.setBalance(50.0);
        cardEntity.setActive(Boolean.TRUE);

        when(cardRepository.findByCardNumber(cardNumber)).thenReturn(Optional.of(cardEntity));
        when(cardRepository.saveAndFlush(any())).thenAnswer(invocation -> invocation.getArgument(0));

        cardService.rechargeBalance(cardNumber, balance);

        assertEquals(150.0, cardEntity.getBalance());
        verify(cardRepository, times(1)).saveAndFlush(cardEntity);
    }

    @Test
    void testRechargeBalanceWhenCardBlocked() {

        String cardNumber = "1234567890123456";
        double balance = 100.0;
        CardEntity cardEntity = new CardEntity();
        cardEntity.setCardNumber(cardNumber);
        cardEntity.setBlocked(true);
        when(cardRepository.findByCardNumber(cardNumber)).thenReturn(Optional.of(cardEntity));

        assertThrows(ResourceBadRequestException.class, () -> cardService.rechargeBalance(cardNumber, balance));
        assertEquals(0.0, cardEntity.getBalance());
        verify(cardRepository, never()).saveAndFlush(any());
    }

    @Test
    void testGetBalance() {

        String cardNumber = "1234567890123456";
        double balance = 100.0;
        CardEntity cardEntity = new CardEntity();
        cardEntity.setCardNumber(cardNumber);
        cardEntity.setBalance(balance);
        when(cardRepository.findByCardNumber(cardNumber)).thenReturn(Optional.of(cardEntity));

        Double result = cardService.getBalance(cardNumber);

        assertEquals(balance, result);
    }

    @Test
    void testGetBalanceWhenCardNotFound() {

        String cardNumber = "1234567890123456";
        when(cardRepository.findByCardNumber(cardNumber)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> cardService.getBalance(cardNumber));
    }

    @Test
    void testGetCardNumber() {

        String cardNumber = "1234567890123456";
        CardEntity cardEntity = new CardEntity();
        cardEntity.setCardNumber(cardNumber);
        when(cardRepository.findByCardNumber(cardNumber)).thenReturn(Optional.of(cardEntity));

        CardEntity result = cardService.getCardNumber(cardNumber);

        assertEquals(cardEntity, result);
    }

    @Test
    void testGetCardNumberWhenCardNotFound() {
        String cardNumber = "1234567890123456";
        when(cardRepository.findByCardNumber(cardNumber)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> cardService.getCardNumber(cardNumber));
    }

    @Test
    void testUpdateCardBalance() {
        String cardNumber = "1234567890123456";
        double newBalance = 200.0;

        cardService.updateCardBalance(cardNumber, newBalance);

        verify(cardRepository, times(1)).updateBalanceByCardNumber(cardNumber, newBalance);
    }
}