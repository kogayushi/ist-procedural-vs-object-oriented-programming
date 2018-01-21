package com.example.ist.object.oriented.domain.model.policy;

import com.example.ist.object.oriented.domain.model.identity.Password;
import com.example.ist.object.oriented.domain.model.identity.Username;

public class NotSameWithCurrentUsernamePolicy extends CredentialPolicy {
    private final Username username;

    public NotSameWithCurrentUsernamePolicy(Username username) {
        this.username = username;
    }

    @Override
    protected boolean notSatisfiedBy(Password password) {
        return this.username.equals(password);
    }
}
