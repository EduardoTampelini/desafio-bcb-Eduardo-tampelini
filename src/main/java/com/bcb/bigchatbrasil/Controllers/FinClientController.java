package com.bcb.bigchatbrasil.Controllers;

import com.bcb.bigchatbrasil.DTO.PlanData;
import com.bcb.bigchatbrasil.Modals.FinClient;
import com.bcb.bigchatbrasil.Repository.ClientRepository;
import com.bcb.bigchatbrasil.Repository.FinClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/fin")
public class FinClientController {

    @Autowired
    private FinClientRepository fcrepository;

    @Autowired
    private ClientRepository clirepository;

    @GetMapping("/{id}")
    public ResponseEntity consultFinCli(@PathVariable UUID id){
        var finclientSearch = fcrepository.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(finclientSearch);
    }

    @PostMapping("/")
    public ResponseEntity create(@RequestBody FinClient finClient){
        var finclient = fcrepository.save(finClient);

        return ResponseEntity.status(HttpStatus.CREATED).body(finClient);
    }

    @GetMapping("balance/{id}")
    public ResponseEntity consultBalance(FinClient finClient,@PathVariable UUID id){
        var finclientSearch = fcrepository.findById(id);
        var balance = finclientSearch.get().getCredit();
        return ResponseEntity.status(HttpStatus.OK).body(balance);
    }

    @PostMapping("credit/{id}")
    public ResponseEntity addCredit(@RequestBody Integer credit,@PathVariable UUID id){
        var finclientSearch = fcrepository.findById(id);
        var valueCredit = finclientSearch.get().getCredit()+credit;
        if(finclientSearch.get().getLimitCredit() < valueCredit || credit == 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Valor invalido! insira outro valor");
        }else{
            finclientSearch.get().setCredit(valueCredit);
            fcrepository.save(finclientSearch.get());
            return ResponseEntity.status(HttpStatus.OK).body(valueCredit);
        }
    }

    @PutMapping("limitClient/{id}")
    public ResponseEntity alterLimitClient(@RequestBody Integer limit, @PathVariable UUID id){
        var finclientSearch = fcrepository.findById(id);
        finclientSearch.get().setLimitCredit(limit);
        fcrepository.save(finclientSearch.get());
        return ResponseEntity.status(HttpStatus.OK).body(limit);
    }

    @PutMapping("plan/{id}")
    public ResponseEntity alterPlan(@RequestBody PlanData data, @PathVariable UUID id) {
        var finclientSearch = fcrepository.findById(id);
        finclientSearch.get().setCredit(data.cred());
        finclientSearch.get().setLimitCredit(data.limitcred());
        finclientSearch.get().setValuePlan(data.value());
        fcrepository.save(finclientSearch.get());
        return ResponseEntity.status(HttpStatus.OK).body("Alteração feita");
    }
}
