package com.example.ist.object.oriented.domain.model.policy;

import com.example.ist.object.oriented.domain.model.identity.Password;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Slf4j
@RequiredArgsConstructor
public class PasswordPolicy {

    private final Set<Policy> policies;

    public void validate(Password password) {
        for (Policy policy : policies) {
            policy.validate(password);
        }
    }
}
