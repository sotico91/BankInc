package com.co.bankInc.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDTO {

    @Schema(description = "The status code of the message", example = "200")
    private int code;

    @Schema(description = "The detailed message", example = "Operation completed successfully")
    private String message;

}
