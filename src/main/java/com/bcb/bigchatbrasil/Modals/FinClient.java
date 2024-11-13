package com.bcb.bigchatbrasil.Modals;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity(name = "tb_fin")
public class FinClient {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private Integer limitCredit;
    private Integer credit;

    @OneToOne
    @JoinColumn(name = "client_fk")
    private ClientModal client;
}
