package com.bcb.bigchatbrasil.Modals;

import com.bcb.bigchatbrasil.Modals.enums.Priority;
import com.bcb.bigchatbrasil.Modals.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity(name = "tb_message")
public class MessageModal {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    @OneToOne
    @NotNull(message = "Cliente não pode ser null")
    @JoinColumn(name = "client_fk")
    private ClientModal client;
    @OneToOne
    @NotNull(message = "recebedor não pode ser null")
    @JoinColumn(name = "recipient_fk")
    private UserModal recipientId;
    private String content;
    private String timestamp;
    @Enumerated(EnumType.STRING)
    private Priority priority = Priority.normal;

    @Enumerated(EnumType.STRING)
    private Status status = Status.queued;
    private BigDecimal cost;

    @ManyToOne
    @NotNull(message = "conversation não pode ser null")
    @JoinColumn(name = "conversation_id")
    private ChatModal conversation;
}
