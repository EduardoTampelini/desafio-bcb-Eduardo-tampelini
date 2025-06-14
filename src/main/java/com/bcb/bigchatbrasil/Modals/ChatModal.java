package com.bcb.bigchatbrasil.Modals;

import com.bcb.bigchatbrasil.Modals.enums.Priority;
import com.bcb.bigchatbrasil.Modals.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "tb_chat")
public class ChatModal {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    @OneToOne
    @JoinColumn(name = "client_fk")
    private ClientModal client;
    @OneToOne
    @JoinColumn(name = "recipient_fk")
    private UserModal recipientId;
    private String lastMessageContent;
    private String lastMessageTime;
    private Integer unreadCount;

    public boolean hasParticipant(UUID userId) {
        return recipientId.getId().equals(userId);
    }
}
