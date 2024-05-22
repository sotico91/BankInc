package com.co.bankInc.model.card.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Message card not found")
public class MessageCardNotFoundDTO {

    @Schema(description = "The status code of the message", example = "404")
    private int code;

    @Schema(description = "The detailed message", example = "Card not found")
    private String message;

}