package com.bcb.bigchatbrasil.Controllers;

import com.bcb.bigchatbrasil.Modals.ChatModal;
import com.bcb.bigchatbrasil.Modals.ClientModal;
import com.bcb.bigchatbrasil.Modals.MessageModal;
import com.bcb.bigchatbrasil.Modals.UserModal;
import com.bcb.bigchatbrasil.Modals.enums.PlanType;
import com.bcb.bigchatbrasil.Modals.enums.Priority;
import com.bcb.bigchatbrasil.Modals.enums.Status;
import com.bcb.bigchatbrasil.DTO.MessageRequestDTO;
import com.bcb.bigchatbrasil.DTO.MessageResponseDTO;
import com.bcb.bigchatbrasil.Repository.ChatRepository;
import com.bcb.bigchatbrasil.Repository.ClientRepository;
import com.bcb.bigchatbrasil.Repository.MessageRepository;
import com.bcb.bigchatbrasil.Repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;


    public MessageController(
                             ChatRepository chatRepository,
                             UserRepository userRepository,
                             MessageRepository messageRepository) {
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;

    }

    @PostMapping
    public ResponseEntity<?> sendMessage(
            @RequestBody MessageRequestDTO messageRequest) {

        MessageModal message = new MessageModal();
        Optional<ChatModal> chat = chatRepository.findById(messageRequest.getChatId());
        if (chat.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Conversa não encontrada com ID: " + messageRequest.getChatId());
        }
        Optional<UserModal> recipient = userRepository.findById(messageRequest.getRecipientId());
        if (recipient.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Destinatário com ID " + messageRequest.getRecipientId() + " não encontrado");
        }
        if (messageRequest.getChatId() == null || chat.get().getClient() == null || messageRequest.getRecipientId() == null) {
            return ResponseEntity.badRequest().body("IDs de conversa, cliente e destinatário são obrigatórios");
        }

        message.setClient(chat.get().getClient());
        message.setConversation(chat.get());
        message.setRecipientId(recipient.get());
        message.setContent(messageRequest.getContent());
        message.setPriority(messageRequest.getPriority());
        message.setTimestamp(String.valueOf(LocalDateTime.now()));

        BigDecimal cost = messageRequest.getPriority() == Priority.urgent
                ? new BigDecimal("0.50")
                : new BigDecimal("0.25");
        message.setCost(cost);
        message.setStatus(Status.queued);

       if(chat.get().getClient().getBalance().compareTo(cost) < 0 && chat.get().getClient().getPlanType() == PlanType.prepaid){
           message.setStatus(Status.failed);
           messageRepository.save(message);
           return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED)
                   .body("Saldo insuficiente para cliente pré-pago");
       } else if(chat.get().getClient().getLimit() < cost.intValue() && chat.get().getClient().getPlanType() == PlanType.postpaid){
           message.setStatus(Status.failed);
           messageRepository.save(message);
           return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED)
                   .body("Limite excedido para cliente pós-pago");
       }else{
           message.setStatus(Status.sent);
           messageRepository.save(message);
           return ResponseEntity.status(201).body(message);
       }

    }

    @GetMapping
    public ResponseEntity<List<MessageModal>> getMessages() {
        List<MessageModal> messages = messageRepository.findAll();
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageModal> getMessage(@PathVariable UUID id) {

        return messageRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}