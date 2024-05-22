package com.co.bankInc.model.transaction.entity;

import com.co.bankInc.model.card.entity.CardEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Schema(description = "message transaction not found")
public class TransactionEntity {

    @Schema(description = "id transaction")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "transaction amount")
    private double amount;

    @Schema(description = "transaction date")
    private LocalDateTime transactionDate;

    @Schema(description = "status transaction")
    private boolean isAnulated;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private CardEntity card;

}