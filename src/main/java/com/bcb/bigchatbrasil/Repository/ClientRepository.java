package com.bcb.bigchatbrasil.Repository;

import com.bcb.bigchatbrasil.Modals.ClientModal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<ClientModal, UUID> {

    Optional<ClientModal> findById(UUID id);

    ClientModal findByEmail(String email);
}
