package com.bcb.bigchatbrasil.Controllers;

import com.bcb.bigchatbrasil.Modals.ClientModal;
import com.bcb.bigchatbrasil.Modals.FinClient;
import com.bcb.bigchatbrasil.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientRepository clirepository;

    @PostMapping("/")
    public ResponseEntity createClient(@RequestBody ClientModal clientModal){
        var clientEmail = clirepository.findByEmail(clientModal.getEmail());
        if(clientEmail != null){
            return ResponseEntity.badRequest().body("Email já cadastrado");
        }
        var client = clirepository.save(clientModal);
        return ResponseEntity.status(HttpStatus.CREATED).body(client);
    }

    @GetMapping("/")
    public  ResponseEntity<List<ClientModal>> list(){
        var list = clirepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity dataClient(@PathVariable UUID id){
        var clientData = clirepository.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(clientData);
    }
}
