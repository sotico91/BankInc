package com.co.bankInc.service.transaction.impl;

import com.co.bankInc.exceptions.impl.ResourceBadRequestException;
import com.co.bankInc.exceptions.impl.ResourceNotFoundException;
import com.co.bankInc.mapper.transaction.TransactionMapper;
import com.co.bankInc.model.card.entity.CardEntity;
import com.co.bankInc.model.transaction.dto.TransactionDTO;
import com.co.bankInc.model.transaction.entity.TransactionEntity;
import com.co.bankInc.repository.transaction.TransactionRepository;
import com.co.bankInc.service.card.CardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private CardService cardService;

    @Mock
    private TransactionMapper transactionMapper;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    private TransactionDTO transactionDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        transactionDTO = TransactionDTO.builder()
                .idTransaction(1L)
                .cardNumber("1234567890123456")
                .price(100.0)
                .build();

    }

    @Test
    void testMakePurchase() {

        String cardNumber = "1234567890123456";
        Double price = 100.0;
        CardEntity cardEntity = new CardEntity();
        cardEntity.setActive(Boolean.TRUE);
        cardEntity.setBalance(500.00);
        TransactionEntity transactionEntity = new TransactionEntity();

        when(cardService.getCardNumber(cardNumber)).thenReturn(cardEntity);
        when(transactionMapper.mapperTransactionDtoToEntity(price, cardEntity)).thenReturn(transactionEntity);
        when(transactionRepository.save(transactionEntity)).thenReturn(transactionEntity);
        when(transactionMapper.mapperTransactionEntityToTransactionDTO(any())).thenReturn(transactionDTO);

        TransactionDTO result = transactionService.makePurchase(cardNumber, price);

        assertNotNull(result);
        verify(cardService, times(1)).updateCardBalance(cardNumber, cardEntity.getBalance() - price);
        verify(transactionRepository, times(1)).save(transactionEntity);
    }

    @Test
    void testGetTransaction() {

        Long idTransaction = 1L;
        TransactionEntity transactionEntity = new TransactionEntity();
        when(transactionRepository.findById(idTransaction)).thenReturn(Optional.of(transactionEntity));
        when(transactionMapper.mapperTransactionEntityToTransactionDTO(transactionEntity)).thenReturn(transactionDTO);

        TransactionDTO result = transactionService.getTransaction(idTransaction);

        assertNotNull(result);
    }

    @Test
    void testAnnulTransaction() {

        String cardNumber = "1234567890123456";
        Long idTransaction = 1L;
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setTransactionDate(LocalDateTime.now().minusHours(12));
        CardEntity cardEntity = new CardEntity();
        when(transactionRepository.findById(idTransaction)).thenReturn(Optional.of(transactionEntity));
        when(cardService.getCardNumber(cardNumber)).thenReturn(cardEntity);
        doNothing().when(transactionRepository).updateTransactionStatus(cardNumber, idTransaction);
        doNothing().when(cardService).updateCardBalance(cardNumber, transactionEntity.getAmount() + cardEntity.getBalance());

        assertDoesNotThrow(() -> transactionService.annulTransaction(cardNumber, idTransaction));

        verify(transactionRepository, times(1)).updateTransactionStatus(cardNumber, idTransaction);
        verify(cardService, times(1)).updateCardBalance(cardNumber, transactionEntity.getAmount() + cardEntity.getBalance());
    }


    @Test
    void testAnnulTransactionWhenTransactionNotFound() {

        String cardNumber = "1234567890123456";
        Long idTransaction = 1L;
        when(transactionRepository.findById(idTransaction)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> transactionService.annulTransaction(cardNumber, idTransaction));
        verify(cardService, never()).getCardNumber(cardNumber);
    }

    @Test
    void testAnnulTransactionWhenTransactionIsAnulated() {

        String cardNumber = "1234567890123456";
        Long idTransaction = 1L;
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setAnulated(true);
        when(transactionRepository.findById(idTransaction)).thenReturn(Optional.of(transactionEntity));

        assertThrows(ResourceBadRequestException.class, () -> transactionService.annulTransaction(cardNumber, idTransaction));
        verify(cardService, never()).getCardNumber(cardNumber);
    }

    @Test
    void testAnnulTransactionWhenTransactionDateIsGreaterThan24Hours() {

        String cardNumber = "1234567890123456";
        Long idTransaction = 1L;
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setTransactionDate(LocalDateTime.now().minusHours(25));
        when(transactionRepository.findById(idTransaction)).thenReturn(Optional.of(transactionEntity));

        assertThrows(ResourceBadRequestException.class, () -> transactionService.annulTransaction(cardNumber, idTransaction));
        verify(cardService, never()).getCardNumber(cardNumber);
    }

}