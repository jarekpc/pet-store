package com.packt.petstore.service;

import com.packt.petstore.model.PetStoreUserDTO;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;

public interface IReactivePetStoreUserService {

    Mono<UserDetails> findByEmail(@NonNull String email);

    Mono<Void> registerNewUser(@NonNull PetStoreUserDTO petStoreUser);
}
