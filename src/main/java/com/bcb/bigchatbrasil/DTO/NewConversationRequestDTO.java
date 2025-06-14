package com.bcb.bigchatbrasil.DTO;

import com.bcb.bigchatbrasil.Modals.ClientModal;
import com.bcb.bigchatbrasil.Modals.UserModal;
import lombok.Data;

import java.util.UUID;

@Data
public class NewConversationRequestDTO {
    private UUID recipientId;
    private UUID clientId;
}