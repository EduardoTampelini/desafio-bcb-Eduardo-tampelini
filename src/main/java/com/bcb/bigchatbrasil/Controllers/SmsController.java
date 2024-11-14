package com.bcb.bigchatbrasil.Controllers;

import com.bcb.bigchatbrasil.Modals.SmsModal;
import com.bcb.bigchatbrasil.Repository.FinClientRepository;
import com.bcb.bigchatbrasil.Repository.SmsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/sms")
public class SmsController {
    @Autowired
    private SmsRepository smsrepository;

    @Autowired
    private FinClientRepository fcrepository;

    @PostMapping("/{id}")
    public ResponseEntity send(@RequestBody SmsModal sms, @PathVariable UUID id){
        var finclientSearch = fcrepository.findById(id);
        if(finclientSearch.get().getCredit() <=0 ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sem crédito");
        }else{
            var smscreated = smsrepository.save(sms);
            finclientSearch.get().setCredit(finclientSearch.get().getCredit() - 1);
            fcrepository.save(finclientSearch.get());

            return ResponseEntity.status(HttpStatus.OK).body(smscreated);
        }
    }
}
