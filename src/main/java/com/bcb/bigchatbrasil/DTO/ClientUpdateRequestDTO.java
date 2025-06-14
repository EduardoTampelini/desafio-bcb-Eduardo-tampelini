package com.bcb.bigchatbrasil.DTO;

import com.bcb.bigchatbrasil.Modals.enums.DocumentType;
import com.bcb.bigchatbrasil.Modals.enums.PlanType;
import lombok.Data;

@Data
public class ClientUpdateRequestDTO {
private String name;
private String documentId;
private DocumentType documentType;
private PlanType planType;
private Integer limit;
private Boolean active;


        }
