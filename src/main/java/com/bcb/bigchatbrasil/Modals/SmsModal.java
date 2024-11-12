package com.bcb.bigchatbrasil.Modals;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Data
@Entity(name = "tb_sms")
public class SmsModal {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String phone;
    private boolean isWhatsApp;
    private String text;

}
