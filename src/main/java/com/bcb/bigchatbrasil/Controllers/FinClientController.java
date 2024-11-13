package com.bcb.bigchatbrasil.Controllers;

import com.bcb.bigchatbrasil.Modals.FinClient;
import com.bcb.bigchatbrasil.Repository.ClientRepository;
import com.bcb.bigchatbrasil.Repository.FinClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/fin")
public class FinClientController {

    @Autowired
    private FinClientRepository fcrepository;

    @Autowired
    private ClientRepository clirepository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody FinClient finClient){
        var finclient = fcrepository.save(finClient);

        return ResponseEntity.status(HttpStatus.CREATED).body(finClient);
    }

    @GetMapping("balance/{id}")
    public ResponseEntity consultBalance(FinClient finClient,@PathVariable UUID id){
        var finclientSearch = fcrepository.findById(id);
        var balance = finclientSearch.get().getLimitCredit() - finclientSearch.get().getCredit();
        return ResponseEntity.status(HttpStatus.OK).body(balance);
    }


}
