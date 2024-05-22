package com.co.bankInc.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CardDTO {

    private String productId;
    private String cardNumber;
    private String cardholderName;
    private String expirationDate;
    private Double balance = 0.0;

}
