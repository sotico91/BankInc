package com.co.bankInc.service.impl;

import com.co.bankInc.exceptions.impl.ResourceBadRequestException;
import com.co.bankInc.exceptions.impl.ResourceNotFoundException;
import com.co.bankInc.mapper.CardMapper;
import com.co.bankInc.model.dto.CardDTO;
import com.co.bankInc.model.entity.CardEntity;
import com.co.bankInc.repository.CardRepository;
import com.co.bankInc.service.CardService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Service
public class CardServiceImpl implements CardService {

    private static final Logger logger = LogManager.getLogger(CardServiceImpl.class);

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardMapper mapper;

    @Override
    public CardDTO generateCardNumber(String productId, String cardHolderName) {

        logger.info("Start generateCardNumber method {},{}",productId, cardHolderName );

        StringBuilder cardNumber = new StringBuilder(productId);
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            cardNumber.append(random.nextInt(10));
        }

        String cardNumberFinal = cardNumber.toString();
        String cardExpirationDate = generateExpirationDate();

        CardDTO cardDTO = CardDTO.builder()
                .productId(productId)
                .cardNumber(cardNumberFinal)
                .cardholderName(cardHolderName)
                .expirationDate(cardExpirationDate)
                .balance(0.0)
                .build();

        CardEntity cardEntityIn = mapper.mapperCardDtoTOEntity(cardDTO);

        CardEntity cardEntityOut = cardRepository.saveAndFlush(cardEntityIn);

        logger.info("End generateCardNumber method ");

        return mapper.mapperCardEntityToDto(cardEntityOut);
    }

    @Override
    public void activateCard(String cardNumber) {

        logger.info("Start activateCard method {}",cardNumber);

        CardEntity cardEntity = getCardNumber(cardNumber);
        cardEntity.setActive(Boolean.TRUE);
        cardEntity.setBlocked(Boolean.FALSE);

        cardRepository.saveAndFlush(cardEntity);

        logger.info("End activateCard method");
    }

    @Override
    public void blockCard(String cardNumber) {

        logger.info("Start blockCard method {}",cardNumber);

        CardEntity cardEntity = getCardNumber(cardNumber);
        cardEntity.setActive(Boolean.FALSE);
        cardEntity.setBlocked(Boolean.TRUE);

        cardRepository.saveAndFlush(cardEntity);

        logger.info("End blockCard method");

    }

    @Override
    public void rechargeBalance(String cardNumber, Double balance) {

        CardEntity cardEntity = getCardNumber(cardNumber);

        logger.info("Start rechargeBalance method");

        if (cardEntity.isActive() && !cardEntity.isBlocked()){

            double newBalance = cardEntity.getBalance() + balance ;
            cardEntity.setBalance(newBalance);

            cardRepository.saveAndFlush(cardEntity);

            logger.info("End rechargeBalance method");

            return;
        }

        logger.error("Error in rechargeBalance method");

        throw new ResourceBadRequestException("The card number: "+cardNumber +" is blocked or inactive");

    }

    @Override
    public Double getBalance(String cardNumber) {
        logger.info("Start getBalance method");

        CardEntity cardEntity = getCardNumber(cardNumber);

        logger.info("End getBalance method");
        return cardEntity.getBalance();
    }

    public CardEntity getCardNumber(String cardNumber){

        logger.info("Start getCardNumber method {}",cardNumber);

        CardEntity cardEntity = cardRepository.findByCardNumber(cardNumber).orElse(null);
        if (cardEntity != null){
            logger.info("End getCardNumber method {}",cardNumber);
            return cardEntity;
        }

        logger.error("Error in getCardNumber method");

        throw new ResourceNotFoundException("The card number: "+cardNumber +" Doesn't exist");

    }

    public String generateExpirationDate() {

        logger.info("Start generateExpirationDate method");

        LocalDate currentDate = LocalDate.now();
        LocalDate expirationDate = currentDate.plusYears(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");

        logger.info("End generateExpirationDate method");

        return expirationDate.format(formatter);
    }

}
