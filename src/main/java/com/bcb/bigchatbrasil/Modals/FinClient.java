package com.bcb.bigchatbrasil.Modals;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Data
@Entity(name = "tb_finclient")
public class FinClient {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private int limit;
    private int credit;
    private ClientModal client;
}
