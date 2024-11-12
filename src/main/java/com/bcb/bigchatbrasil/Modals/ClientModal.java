package com.bcb.bigchatbrasil.Modals;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import java.util.UUID;

@Data
@Entity(name = "tb_client")
public class ClientModal {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String name;
    @Email
    private String email;
    @CPF
    private String cpf;
    private String phone;
    @CNPJ
    private String  cnpj;
    private String nameCompany;


}
