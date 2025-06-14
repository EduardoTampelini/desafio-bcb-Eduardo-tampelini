package com.bcb.bigchatbrasil.DTO;

import com.bcb.bigchatbrasil.Modals.enums.Status;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class MessageResponseDTO {
    private UUID id;
    private Status status;
    private OffsetDateTime estimatedDelivery;
    private BigDecimal cost;
    private BigDecimal currentBalance; // apenas para pré-pago
}