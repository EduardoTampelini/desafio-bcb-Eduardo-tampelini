package com.bcb.bigchatbrasil.Controllers;

import com.bcb.bigchatbrasil.DTO.ClientBalanceResponseDTO;
import com.bcb.bigchatbrasil.DTO.ClientRequestDTO;
import com.bcb.bigchatbrasil.DTO.ClientUpdateRequestDTO;
import com.bcb.bigchatbrasil.Modals.ClientModal;
import com.bcb.bigchatbrasil.Modals.UserModal;
import com.bcb.bigchatbrasil.Repository.ClientRepository;
import com.bcb.bigchatbrasil.Repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientRepository clientRepository;
    private final UserRepository userRepository;

    public ClientController(ClientRepository clientRepository, UserRepository userRepository) {
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<ClientModal> createClient(
            @RequestBody ClientRequestDTO request) {

        UserModal user = new UserModal();
        user.setName(request.getName());
        user = userRepository.save(user);

        ClientModal client = new ClientModal();
        client.setUserid(user);
        client.setDocumentID(String.valueOf(request.getDocumentId()));
        client.setDocumentType(request.getDocumentType());
        client.setPlanType(request.getPlanType());
        client.setBalance(request.getInitialBalance());
        client.setLimit(request.getLimit());
        client.setActive(true);

        client = clientRepository.save(client);
        return ResponseEntity.ok(client);
    }


    @GetMapping
    public ResponseEntity<List<ClientModal>> getAllClients() {
        List<ClientModal> clients = clientRepository.findAll();
        return ResponseEntity.ok(clients);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ClientModal> getClient(@PathVariable UUID id) {
        Optional<ClientModal> client = clientRepository.findById(id);
        return client.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<ClientModal> updateClient(
            @PathVariable UUID id,
            @RequestBody ClientUpdateRequestDTO request) {

        return clientRepository.findById(id)
                .map(client -> {
                    if (request.getName() != null) {
                        client.getUserid().setName(request.getName());
                    }
                    if (request.getDocumentId() != null) {
                        client.setDocumentID(request.getDocumentId());
                    }
                    if (request.getDocumentType() != null) {
                        client.setDocumentType(request.getDocumentType());
                    }
                    if (request.getPlanType() != null) {
                        client.setPlanType(request.getPlanType());
                    }
                    if (request.getLimit() != null) {
                        client.setLimit(request.getLimit());
                    }
                    if (request.getActive() != null) {
                        client.setActive(request.getActive());
                    }

                    client = clientRepository.save(client);
                    return ResponseEntity.ok(client);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/balance")
    public ResponseEntity<ClientBalanceResponseDTO> getClientBalance(@PathVariable UUID id) {
        return clientRepository.findById(id)
                .map(client -> {
                    ClientBalanceResponseDTO response = new ClientBalanceResponseDTO();
                    response.setBalance(client.getBalance());
                    response.setLimit(client.getLimit());
                    response.setPlanType(client.getPlanType());
                    return ResponseEntity.ok(response);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
