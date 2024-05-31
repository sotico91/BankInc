package com.co.bankInc.model.card.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Schema(description = "Card details")
public class CardDTO {

    @Schema(description = "product id")
    private String productId;

    @Schema(description = "card number")
    private String cardNumber;

    @Schema(description = "card holder name")
    private String cardholderName;

    @Schema(description = "card expiration date")
    private String expirationDate;

    @Schema(description = "current balance of card")
    private Double balance = 0.0;

}