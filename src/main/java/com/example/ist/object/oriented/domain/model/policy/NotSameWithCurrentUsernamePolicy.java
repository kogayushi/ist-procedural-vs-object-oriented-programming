package com.example.ist.object.oriented.domain.model.policy;

import com.example.ist.object.oriented.domain.model.identity.AuthenticationFactor;

public class NotSameWithCurrentUsernamePolicy extends Policy {
    private final AuthenticationFactor username;

    public NotSameWithCurrentUsernamePolicy(AuthenticationFactor username) {
        this.username = username;
    }

    @Override
    protected boolean notSatisfiedBy(AuthenticationFactor factor) {
        return this.username.equals(factor);
    }
}
