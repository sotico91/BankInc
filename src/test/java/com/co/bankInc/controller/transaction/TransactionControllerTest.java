package com.co.bankInc.controller.transaction;

import com.co.bankInc.model.generalMessage.MessageDTO;
import com.co.bankInc.model.transaction.dto.TransactionDTO;
import com.co.bankInc.service.transaction.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TransactionControllerTest {

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    private TransactionDTO request;

    private TransactionDTO response;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        request = TransactionDTO.builder()
                .idTransaction(1234L)
                .cardNumber("1234567890123456")
                .price(100.0)
                .build();

        response = TransactionDTO.builder()
                .idTransaction(1234L)
                .build();

    }

    @Test
    void testPurchaseTransaction() {

        when(transactionService.makePurchase("1234567890123456", 100.0)).thenReturn(response);

        ResponseEntity<MessageDTO> responseEntity = transactionController.purchaseTransaction(request);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("Successful purchase, the number transaction is: 1234", Objects.requireNonNull(responseEntity.getBody()).getMessage());
        assertEquals(6, responseEntity.getBody().getCode());
    }

    @Test
    void testGetTransaction() {

        Long transactionId = 1234L;

        when(transactionService.getTransaction(transactionId)).thenReturn(response);

        ResponseEntity<TransactionDTO> responseEntity = transactionController.getTransaction(transactionId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(transactionId, Objects.requireNonNull(responseEntity.getBody()).getIdTransaction());
    }

    @Test
    void testAnnulTransaction() {

        ResponseEntity<MessageDTO> responseEntity = transactionController.annulTransaction(request);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("transaction successfully cancelled", Objects.requireNonNull(responseEntity.getBody()).getMessage());
        assertEquals(7, responseEntity.getBody().getCode());

        verify(transactionService, times(1)).annulTransaction("1234567890123456", 1234L);
    }
}