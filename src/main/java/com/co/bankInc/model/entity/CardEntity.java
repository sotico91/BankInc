package com.co.bankInc.model.entity;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class CardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;
    private String productId;
    private String cardNumber;
    private String cardholderName;
    private String expirationDate;
    private boolean isActive;
    private boolean isBlocked;
    private double balance;
    private boolean allowsDollarTransactions = true;

}