package com.bcb.bigchatbrasil.Modals;

import com.bcb.bigchatbrasil.Modals.enums.DocumentType;
import com.bcb.bigchatbrasil.Modals.enums.PlanType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "tb_client")
public class ClientModal {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    @OneToOne
    @JoinColumn(name = "user_fk")
    private UserModal userid;
    private String documentID;
    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

    @Enumerated(EnumType.STRING)
    private PlanType planType;
    private BigDecimal balance;
    @Column(name = "credit_limit")
    private Integer limit;
    private boolean active;


}
