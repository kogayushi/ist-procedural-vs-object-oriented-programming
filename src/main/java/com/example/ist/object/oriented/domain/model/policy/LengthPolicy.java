package com.example.ist.object.oriented.domain.model.policy;

import com.example.ist.object.oriented.domain.model.identity.AuthenticationFactor;

public class LengthPolicy extends CredentialPolicy<AuthenticationFactor> {
    private final int min;
    private final int max;

    public LengthPolicy(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    protected boolean notSatisfiedBy(AuthenticationFactor factor) {
        int length = factor.getValue().length();
        return this.min > length || length > max;
    }
}
