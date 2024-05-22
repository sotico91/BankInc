package com.co.bankInc.model.card.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
@Schema(description = "card details entity")
public class CardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Internal id card")
    private Long cardId;

    @Schema(description = "product id")
    private String productId;

    @Schema(description = "card number")
    private String cardNumber;

    @Schema(description = "card holder name")
    private String cardholderName;

    @Schema(description = "expiration date card")
    private String expirationDate;

    @Schema(description = "active or inactive status card")
    private boolean isActive;

    @Schema(description = "block status card")
    private boolean isBlocked;

    @Schema(description = "current balance card")
    private double balance;

    @Schema(description = "allow dollar transaction")
    private boolean allowsDollarTransactions = true;

}