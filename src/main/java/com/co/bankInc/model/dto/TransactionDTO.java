package com.co.bankInc.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TransactionDTO {

    private String idTransaction;
    private String cardNumber;
    private Double price;

}
