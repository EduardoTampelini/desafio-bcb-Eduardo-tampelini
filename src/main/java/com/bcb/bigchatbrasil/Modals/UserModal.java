package com.bcb.bigchatbrasil.Modals;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "tb_user")
public class UserModal {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String name;

}
