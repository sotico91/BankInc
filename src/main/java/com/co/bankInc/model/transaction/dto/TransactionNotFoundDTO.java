package com.co.bankInc.model.transaction.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "message transaction not found")
public class TransactionNotFoundDTO {

    @Schema(description = "The status code of the message", example = "404")
    private int code;

    @Schema(description = "The detailed message", example = "Transaction not found")
    private String message;

}