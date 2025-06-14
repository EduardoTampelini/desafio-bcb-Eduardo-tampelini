package com.bcb.bigchatbrasil.Repository;

import com.bcb.bigchatbrasil.Modals.UserModal;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModal, UUID> {


}
