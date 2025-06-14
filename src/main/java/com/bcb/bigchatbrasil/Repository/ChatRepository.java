package com.bcb.bigchatbrasil.Repository;

import com.bcb.bigchatbrasil.Modals.ChatModal;
import com.bcb.bigchatbrasil.Modals.MessageModal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChatRepository extends JpaRepository<ChatModal, UUID> {
    Optional<ChatModal> findById(UUID id);

}
