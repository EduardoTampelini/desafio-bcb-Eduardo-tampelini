package com.bcb.bigchatbrasil.Repository;

import com.bcb.bigchatbrasil.Modals.ClientModal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<ClientModal, UUID> {
}
