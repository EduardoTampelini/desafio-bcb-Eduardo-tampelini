package com.bcb.bigchatbrasil.Controllers;

import com.bcb.bigchatbrasil.DTO.NewConversationRequestDTO;
import com.bcb.bigchatbrasil.Modals.ChatModal;
import com.bcb.bigchatbrasil.Modals.ClientModal;
import com.bcb.bigchatbrasil.Modals.MessageModal;
import com.bcb.bigchatbrasil.Modals.UserModal;
import com.bcb.bigchatbrasil.Modals.enums.Status;
import com.bcb.bigchatbrasil.Repository.ChatRepository;
import com.bcb.bigchatbrasil.Repository.ClientRepository;
import com.bcb.bigchatbrasil.Repository.MessageRepository;
import com.bcb.bigchatbrasil.Repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/conversations")
public class ConversationController {

    private final ChatRepository chatRepository;
    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;


    public ConversationController(ChatRepository chatRepository,
                                  ClientRepository clientRepository,
                                  MessageRepository messageRepository,
                                  UserRepository userRepository) {
        this.chatRepository = chatRepository;
        this.clientRepository = clientRepository;
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;

    }

    @PostMapping
    public ResponseEntity<?> createConversation(
            @RequestBody NewConversationRequestDTO request) {
        Optional<ClientModal> client = clientRepository.findById(request.getClientId());
        if (client.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cliente com ID " + request.getClientId() + " não encontrado");
        }

        // Verifica se o destinatário existe
        Optional<UserModal> recipient = userRepository.findById(request.getRecipientId());
        if (recipient.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Destinatário com ID " + request.getRecipientId() + " não encontrado");
        }
        ChatModal newConversation = new ChatModal();
        newConversation.setClient(client.get());
        newConversation.setRecipientId(recipient.get());
        newConversation.setLastMessageContent("");
        newConversation.setLastMessageTime(null);
        newConversation.setUnreadCount(0);
        ChatModal savedConversation = chatRepository.save(newConversation);
        return ResponseEntity.ok(savedConversation);
    }

    @GetMapping
    public ResponseEntity<List<ChatModal>> getClientConversations() {
        List<ChatModal> conversations = chatRepository.findAll();
        return ResponseEntity.ok(conversations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChatModal> getConversation(@PathVariable UUID id) {
        return chatRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/messages")
    public ResponseEntity<List<MessageModal>> getConversationMessages(@PathVariable UUID id) {
        List<MessageModal> mensagens = messageRepository.findByConversation_Id(id);
        return ResponseEntity.ok(mensagens);
    }



}