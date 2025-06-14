package com.bcb.bigchatbrasil.Controllers;

import com.bcb.bigchatbrasil.Modals.ClientModal;
import com.bcb.bigchatbrasil.Modals.MessageModal;
import com.bcb.bigchatbrasil.Modals.enums.Status;
import com.bcb.bigchatbrasil.Repository.ChatRepository;
import com.bcb.bigchatbrasil.Repository.MessageRepository;
import com.bcb.bigchatbrasil.Repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/queue")
public class QueueController {
    private final MessageRepository messageRepository;

    public QueueController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;

    }
    @GetMapping
    public ResponseEntity<?> getAllQueuedMessages() {
        List<MessageModal> mensagens = messageRepository.findByStatusNot(Status.sent);

        if (mensagens.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(mensagens);
    }

}
