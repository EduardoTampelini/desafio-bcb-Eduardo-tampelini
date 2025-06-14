package com.bcb.bigchatbrasil.DTO;

import com.bcb.bigchatbrasil.Modals.ChatModal;
import com.bcb.bigchatbrasil.Modals.ClientModal;
import com.bcb.bigchatbrasil.Modals.UserModal;
import com.bcb.bigchatbrasil.Modals.enums.Priority;
import lombok.Data;

import java.util.UUID;

@Data
public class MessageRequestDTO {
    private UUID chatId;
    private UUID recipientId;
    private String content;
    private Priority priority;
}