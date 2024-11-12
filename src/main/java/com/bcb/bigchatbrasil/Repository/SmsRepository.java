package com.bcb.bigchatbrasil.Repository;

import com.bcb.bigchatbrasil.Modals.SmsModal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SmsRepository extends JpaRepository<SmsModal, UUID> {
}
