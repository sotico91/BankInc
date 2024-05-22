package com.co.bankInc.controller.card;

import com.co.bankInc.model.card.dto.CardDTO;
import com.co.bankInc.model.generalMessage.MessageDTO;
import com.co.bankInc.service.card.CardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class CardControllerTest {

    @Mock
    private CardService cardService;

    @InjectMocks
    private CardController cardController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGeneratedCardNumber() {

        CardDTO mockCardDTO = CardDTO.builder()
                .productId("123456")
                .cardNumber("1234567890123456")
                .cardholderName("Edgar Velasco")
                .expirationDate("12/25")
                .build();

        when(cardService.generateCardNumber(anyString(), anyString())).thenReturn(mockCardDTO);

        ResponseEntity<CardDTO> response = cardController.generatedCardNumber("123456", "1234567890123456");

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockCardDTO, response.getBody());
    }

    @Test
    void testEnrollCard() {

        CardDTO mockCardDTO = CardDTO.builder()
                .productId("123456")
                .cardNumber("1234567890123456")
                .cardholderName("Edgar Velasco")
                .expirationDate("12/25")
                .build();

        MessageDTO expectedMessage = new MessageDTO();
        expectedMessage.setCode(2);
        expectedMessage.setMessage("The card is activated");

        doNothing().when(cardService).activateCard(anyString());

        ResponseEntity<MessageDTO> response = cardController.enrollCard(mockCardDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedMessage.getMessage(), Objects.requireNonNull(response.getBody()).getMessage());
    }

    @Test
    void testBlockCard() {

        String cardNumber = "1234567890123456";

        MessageDTO expectedMessage = new MessageDTO();
        expectedMessage.setCode(3);
        expectedMessage.setMessage("The Card is blocked");

        doNothing().when(cardService).blockCard(anyString());

        ResponseEntity<MessageDTO> response = cardController.blockCard(cardNumber);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedMessage.getMessage(), Objects.requireNonNull(response.getBody()).getMessage());
    }

    @Test
    void testRechargeBalance() {

        CardDTO mockCardDTO = CardDTO.builder()
                .productId("123456")
                .cardNumber("1234567890123456")
                .cardholderName("Edgar Velasco")
                .expirationDate("12/25")
                .balance(100.0)
                .build();

        double balance = 100.0;

        MessageDTO expectedMessage = new MessageDTO();
        expectedMessage.setCode(4);
        expectedMessage.setMessage("Recharge is successful " + balance);

        doNothing().when(cardService).rechargeBalance(anyString(), anyDouble());

        ResponseEntity<MessageDTO> response = cardController.rechargeBalance(mockCardDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedMessage.getMessage(), Objects.requireNonNull(response.getBody()).getMessage());
    }

    @Test
    void testGetBalance() {

        String cardNumber = "1234567890123456";
        double balance = 500.0;

        MessageDTO expectedMessage = new MessageDTO();
        expectedMessage.setCode(5);
        expectedMessage.setMessage("The balance is: " + balance);

        when(cardService.getBalance(anyString())).thenReturn(balance);

        ResponseEntity<MessageDTO> response = cardController.getBalance(cardNumber);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedMessage.getMessage(), Objects.requireNonNull(response.getBody()).getMessage());
    }

}