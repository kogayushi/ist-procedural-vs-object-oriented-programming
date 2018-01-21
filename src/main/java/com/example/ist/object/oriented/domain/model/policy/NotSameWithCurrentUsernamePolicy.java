package com.example.ist.object.oriented.domain.model.policy;

import com.example.ist.object.oriented.domain.model.identity.AuthenticationFactor;
import com.example.ist.object.oriented.domain.model.identity.Username;

public class NotSameWithCurrentUsernamePolicy extends CredentialPolicy<AuthenticationFactor> {
    private final Username username;

    public NotSameWithCurrentUsernamePolicy(Username username) {
        this.username = username;
    }

    @Override
    protected boolean notSatisfiedBy(AuthenticationFactor factor) {
        return factor.getValue().equals(this.username.getValue());
    }
}
