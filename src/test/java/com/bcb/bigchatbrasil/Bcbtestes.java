package com.bcb.bigchatbrasil;
import com.bcb.bigchatbrasil.Controllers.FinClientController;
import com.bcb.bigchatbrasil.Modals.ClientModal;
import com.bcb.bigchatbrasil.Modals.FinClient;
import com.bcb.bigchatbrasil.Repository.FinClientRepository;
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
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class Bcbtestes {

    @Mock
    private FinClientRepository fcrepository;

    @InjectMocks
    private FinClientController constructorli;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void limitedeCredito() {

        var cli = new ClientModal(UUID.randomUUID(), "ab", "abs@gmail.com", "000000000", "0000000", "00000000", "testelda");
        var fincli = new FinClient(UUID.randomUUID(), 12, 11, new BigDecimal("100.00"), cli);


        when(fcrepository.findById(fincli.getId())).thenReturn(Optional.of(fincli));
        ResponseEntity<?> action = constructorli.addCredit(2, fincli.getId());
        assertEquals(HttpStatus.BAD_REQUEST, action.getStatusCode());
    }
}
