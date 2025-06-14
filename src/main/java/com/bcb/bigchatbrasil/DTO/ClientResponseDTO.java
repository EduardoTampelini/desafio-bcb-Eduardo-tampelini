package com.bcb.bigchatbrasil.DTO;

import com.bcb.bigchatbrasil.Modals.enums.DocumentType;
import com.bcb.bigchatbrasil.Modals.enums.PlanType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class ClientResponseDTO {
    private UUID id;
    private String name;
    private String documentId;
    private DocumentType documentType;
    private PlanType planType;
    private BigDecimal balance;
    private Integer limit;
    private boolean active;
}