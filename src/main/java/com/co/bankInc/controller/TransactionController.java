package com.co.bankInc.controller;

import com.co.bankInc.model.dto.CardDTO;
import com.co.bankInc.model.dto.MessageDTO;
import com.co.bankInc.model.dto.TransactionDTO;
import com.co.bankInc.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final static int IDSERVICE6 =6;
    private final static String PURCHASE = "Purchase is";
    private final static int IDSERVICE7 =7;
    private final static String GETTRANSACTION = "The transaction is";

    @Autowired
    private TransactionService transactionService;

    @Operation(summary = "Purchase transaction")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Purchase transaction successful",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageDTO.class))})
    })
    @PostMapping("/purchase")
    public ResponseEntity<String> purchaseTransaction(@RequestBody TransactionDTO request) {

        TransactionDTO transactionDTO = transactionService.makePurchase(request.getCardNumber(), request.getPrice());

        return null;
    }


    @Operation(summary = "Get transaction")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Return transaction",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TransactionDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Transaction not found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageDTO.class))})
    })
    @GetMapping("/{transactionId}")
    public ResponseEntity<TransactionDTO> getTransaction(@PathVariable("transactionId") Long transactionId) {
        TransactionDTO transaction = transactionService.getTransaction(transactionId);
        return ResponseEntity.ok().body(transaction);
    }


    @Operation(summary = "Void transaction")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reverse transaction - void transaction",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CardDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Transaction not found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageDTO.class))})
    })
    @PostMapping("/anulation")
    public ResponseEntity<Void> annulTransaction(@RequestBody TransactionDTO request) {
        transactionService.annulTransaction(request.getCardNumber(), request.getIdTransaction());
        return ResponseEntity.ok().build();
    }


}
