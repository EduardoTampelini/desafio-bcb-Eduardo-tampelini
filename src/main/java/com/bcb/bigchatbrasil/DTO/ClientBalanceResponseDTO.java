package com.bcb.bigchatbrasil.DTO;

import com.bcb.bigchatbrasil.Modals.enums.PlanType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ClientBalanceResponseDTO {
    private BigDecimal balance;
    private Integer limit;
    private PlanType planType;

    // getters e setters
}
