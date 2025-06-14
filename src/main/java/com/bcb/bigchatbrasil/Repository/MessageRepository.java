package com.bcb.bigchatbrasil.Repository;

import com.bcb.bigchatbrasil.Modals.MessageModal;
import com.bcb.bigchatbrasil.Modals.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface MessageRepository extends JpaRepository<MessageModal, UUID> {

    Optional<MessageModal> findById(UUID uuid);

    List<MessageModal> findByConversation_Id(UUID conversationId);


    List<MessageModal> findByStatusNot(Status status);
}
