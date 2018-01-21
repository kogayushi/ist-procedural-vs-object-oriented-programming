package com.example.ist.procedural.controller;


import com.example.ist.object.oriented.domain.model.identity.User;
import com.example.ist.object.oriented.domain.model.identity.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * it's dummy just for package description.
 */
@RequiredArgsConstructor
@RestController
public class IdentityController {

    private final UserRepository userRepository;

    @GetMapping("/oop/{id}")
    public User getUser(@PathVariable("id") String id) {
        return userRepository.userFromId(UUID.fromString(id));
    }
}