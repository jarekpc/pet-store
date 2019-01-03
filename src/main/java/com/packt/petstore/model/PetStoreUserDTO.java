package com.packt.petstore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class PetStoreUserDTO {

    private String email;
    private String password;
    private String name;


}
