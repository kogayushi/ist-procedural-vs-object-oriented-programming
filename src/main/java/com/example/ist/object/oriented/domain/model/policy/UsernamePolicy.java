package com.example.ist.object.oriented.domain.model.policy;

import com.example.ist.object.oriented.domain.model.identity.Username;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Slf4j
@RequiredArgsConstructor
public class UsernamePolicy extends CredentialPolicy {

    private final Set<CredentialPolicy> policies;

    @Override
    protected boolean notSatisfiedBy(Username username) {
        for (CredentialPolicy policy : policies) {
            if (policy.notSatisfiedBy(username)) {
                return true;
            }
        }
        return false;
    }
}
