package com.example.ist.object.oriented.domain.model.policy;

import com.example.ist.object.oriented.domain.model.identity.Password;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Slf4j
@RequiredArgsConstructor
public class PasswordPolicy extends Policy<Password> {

    private final Set<Policy> policies;

    @Override
    protected boolean notSatisfiedBy(Password password) {
        for (Policy policy : policies) {
            if (policy.notSatisfiedBy(password)) {
                return true;
            }
        }
    }
}
