package com.co.bankInc.controller.transaction;

import com.co.bankInc.model.generalMessage.MessageBadRequestDTO;
import com.co.bankInc.model.generalMessage.MessageDTO;
import com.co.bankInc.model.generalMessage.MessageInternalErrorDTO;
import com.co.bankInc.model.transaction.dto.TransactionDTO;
import com.co.bankInc.model.transaction.dto.TransactionNotFoundDTO;
import com.co.bankInc.service.transaction.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {

    private final static int IDSERVICE6 =6;
    private final static String PURCHASE = "Successful purchase, the number transaction is:";
    private final static int IDSERVICE7 =7;
    private final static String REVERSE = "transaction successfully cancelled";


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
    public ResponseEntity<MessageDTO> purchaseTransaction(@RequestBody TransactionDTO request) {

        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setCode(IDSERVICE6);

        TransactionDTO transactionDTO = transactionService.makePurchase(request.getCardNumber(), request.getPrice());
        messageDTO.setMessage(PURCHASE +" " +transactionDTO.getIdTransaction());
        return ResponseEntity.status(HttpStatus.CREATED).body(messageDTO);
    }


    @Operation(summary = "Get transaction")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Return transaction",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TransactionDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageBadRequestDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Transaction not found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TransactionNotFoundDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageInternalErrorDTO.class))})
    })
    @GetMapping("/{transactionId}")
    public ResponseEntity<TransactionDTO> getTransaction(@PathVariable("transactionId") Long transactionId) {
        TransactionDTO transaction = transactionService.getTransaction(transactionId);
        return ResponseEntity.ok().body(transaction);
    }


    @Operation(summary = "Void transaction")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reverse transaction successful",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageBadRequestDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Transaction not found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TransactionNotFoundDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageInternalErrorDTO.class))})
    })
    @PostMapping("/anulation")
    public ResponseEntity<MessageDTO> annulTransaction(@RequestBody TransactionDTO request) {

        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setCode(IDSERVICE7);

        transactionService.annulTransaction(request.getCardNumber(), request.getIdTransaction());
        messageDTO.setMessage(REVERSE);

        return ResponseEntity.status(HttpStatus.OK).body(messageDTO);
    }
}
