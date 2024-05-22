package com.co.bankInc.controller;

import com.co.bankInc.model.dto.CardDTO;
import com.co.bankInc.model.dto.MessageDTO;
import com.co.bankInc.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class CardController {

    private final static int IDSERVICE2 =2;
    private final static String ACTIVECARD = "The card is activated";
    private final static int IDSERVICE3 =3;
    private final static String BLOCKCARD = "The Card is blocked";
    private final static int IDSERVICE4 =4;
    private final static String RECHARGECARD = "Recharge is successful";
    private final static int IDSERVICE5 =5;
    private final static String BALANCECARD = "The balance is: ";


    @Autowired
    private CardService cardService;

    @Operation(summary = "Generate card number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Generate card number successful",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CardDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Card not found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageDTO.class))})
    })
    @GetMapping("/{productId}/number")
    public ResponseEntity<CardDTO> generatedCardNumber(@PathVariable String productId, @RequestParam  String cardholderName){
        CardDTO cardDTO = cardService.generateCardNumber(productId, cardholderName);
        return ResponseEntity.status(HttpStatus.CREATED).body(cardDTO);
    }

    @Operation(summary = "Activate card")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Activate Card successful",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Card not found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageDTO.class))})
    })
    @PostMapping("/enroll")
    public ResponseEntity<MessageDTO> enrollCard(@RequestBody CardDTO cardDTO) {

        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setCode(IDSERVICE2);
        messageDTO.setMessage(ACTIVECARD);

        cardService.activateCard(cardDTO.getCardNumber());
        return ResponseEntity.ok().body(messageDTO);
    }

    @Operation(summary = "Block card")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Block Card successful",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Card not found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageDTO.class))})
    })
    @DeleteMapping("/{cardNumber}")
    public ResponseEntity<MessageDTO> blockCard(@PathVariable("cardNumber") String cardNumber) {

        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setCode(IDSERVICE3);
        messageDTO.setMessage(BLOCKCARD);

        cardService.blockCard(cardNumber);

        return ResponseEntity.ok().body(messageDTO);
    }

    @Operation(summary = "Card recharged")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Card recharged successful",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Card not found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageDTO.class))})
    })
    @PostMapping("/balance")
    public ResponseEntity<MessageDTO> rechargeBalance(@RequestBody CardDTO cardDTO) {

        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setCode(IDSERVICE4);

        cardService.rechargeBalance(cardDTO.getCardNumber(), cardDTO.getBalance());
        messageDTO.setMessage(RECHARGECARD +" " +cardDTO.getBalance());

        return ResponseEntity.ok().body(messageDTO);
    }

    @Operation(summary = "Get balance card")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get balance card",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Card not found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageDTO.class))}),
    })
    @GetMapping("/balance/{cardNumber}")
    public ResponseEntity<MessageDTO> getBalance(@PathVariable("cardNumber") String cardNumber) {

        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setCode(IDSERVICE5);

        Double balance = cardService.getBalance(cardNumber);

        messageDTO.setMessage(BALANCECARD + balance);
        return ResponseEntity.ok().body(messageDTO);

    }
}
