package com.bcb.bigchatbrasil.DTO;

import com.bcb.bigchatbrasil.Modals.enums.DocumentType;
import com.bcb.bigchatbrasil.Modals.enums.PlanType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ClientRequestDTO {
    private String name;
    private String documentId;
    private DocumentType documentType;
    private PlanType planType;
    private BigDecimal initialBalance;
    private Integer limit;

    // getters e setters
}
