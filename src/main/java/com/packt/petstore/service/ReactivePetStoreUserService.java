package com.packt.petstore.service;

import com.packt.petstore.exception.ExistingAccountException;

import com.packt.petstore.exception.UserNameNotFoundException;
import com.packt.petstore.model.PetStoreUser;
import com.packt.petstore.model.PetStoreUserDTO;
import com.packt.petstore.repository.PetStoreUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ReactivePetStoreUserService implements IReactivePetStoreUserService {

    @Autowired
    private PetStoreUserRepository petStoreUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Mono<UserDetails> findByEmail(String email) {
        return this.petStoreUserRepository.findByEmail(email).map(petStoreUser ->
                User.withUsername(email).password(petStoreUser.getPassword()).roles("USER").build()).switchIfEmpty(Mono.defer(() -> Mono.error(new UserNameNotFoundException("Not found "))));

    }

    @Override
    public Mono<Void> registerNewUser(PetStoreUserDTO dto) {
        System.out.println("Dane do zapisu " + dto.getEmail() + " " + dto.getName() + " " + dto.getPassword());
        return this.petStoreUserRepository.findByEmail(dto.getEmail()).flatMap(existingUser -> Mono.error(new ExistingAccountException("Email is already!")))
                .switchIfEmpty(Mono.defer(() -> createPetStoreUser(dto))).flatMap(user ->
                        petStoreUserRepository.save((PetStoreUser) user)
                ).then();
    }


    private Mono<PetStoreUser> createPetStoreUser(PetStoreUserDTO dto) {
        return Mono.just(new PetStoreUser(dto.getName(), 10_000l, dto.getEmail(), passwordEncoder.encode(dto.getPassword())));
    }
}
