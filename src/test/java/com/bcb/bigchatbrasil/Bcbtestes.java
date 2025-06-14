package com.bcb.bigchatbrasil;

import com.bcb.bigchatbrasil.Controllers.ClientController;
import com.bcb.bigchatbrasil.DTO.ClientBalanceResponseDTO;
import com.bcb.bigchatbrasil.DTO.ClientRequestDTO;
import com.bcb.bigchatbrasil.DTO.ClientUpdateRequestDTO;
import com.bcb.bigchatbrasil.Modals.ClientModal;
import com.bcb.bigchatbrasil.Modals.UserModal;
import com.bcb.bigchatbrasil.Modals.enums.DocumentType;
import com.bcb.bigchatbrasil.Modals.enums.PlanType;
import com.bcb.bigchatbrasil.Repository.ClientRepository;
import com.bcb.bigchatbrasil.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class Bcbtestes {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ClientController clientController;

    private UUID testId;
    private ClientModal testClient;
    private UserModal testUser;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        testId = UUID.randomUUID();

        testUser = new UserModal();
        testUser.setId(testId);
        testUser.setName("Test User");

        testClient = new ClientModal();
        testClient.setId(testId);
        testClient.setUserid(testUser);
        testClient.setDocumentID("123456789");
        testClient.setDocumentType(DocumentType.CPF);
        testClient.setPlanType(PlanType.prepaid);
        testClient.setBalance(BigDecimal.valueOf(1000.0));
        testClient.setLimit(5000);
        testClient.setActive(true);
    }

    @Test
    void createClient_ShouldReturnCreatedClient() {
        // Arrange
        ClientRequestDTO request = new ClientRequestDTO();
        request.setName("New Client");
        request.setDocumentId("987654321");
        request.setDocumentType(DocumentType.CPF);
        request.setPlanType(PlanType.prepaid);
        request.setInitialBalance(BigDecimal.valueOf(500.00));
        request.setLimit(2000);

        when(userRepository.save(any(UserModal.class))).thenReturn(testUser);
        when(clientRepository.save(any(ClientModal.class))).thenReturn(testClient);


        ResponseEntity<ClientModal> response = clientController.createClient(request);


        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(testId, response.getBody().getId());

        verify(userRepository, times(1)).save(any(UserModal.class));
        verify(clientRepository, times(1)).save(any(ClientModal.class));
    }



}