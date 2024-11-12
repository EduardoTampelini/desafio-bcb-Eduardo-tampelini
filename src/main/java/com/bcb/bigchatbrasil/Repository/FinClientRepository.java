package com.bcb.bigchatbrasil.Repository;

import com.bcb.bigchatbrasil.Modals.FinClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FinClientRepository extends JpaRepository<FinClient, UUID> {
}
