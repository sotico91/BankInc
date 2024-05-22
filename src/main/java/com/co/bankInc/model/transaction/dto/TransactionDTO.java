package com.co.bankInc.model.transaction.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Schema(description = "transaction details")
public class TransactionDTO {

    @Schema(description = "id transaction")
    private Long idTransaction;

    @Schema(description = "card number associated with transaction")
    private String cardNumber;

    @Schema(description = "price of purchase")
    private Double price;

}
