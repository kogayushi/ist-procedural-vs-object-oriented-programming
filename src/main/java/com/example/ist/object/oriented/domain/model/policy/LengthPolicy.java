package com.example.ist.object.oriented.domain.model.policy;

import com.example.ist.object.oriented.domain.model.identity.Password;

public class LengthPolicy extends CredentialPolicy {
    private final int min;
    private final int max;

    public LengthPolicy(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    protected boolean notSatisfiedBy(Password password) {
        int length = password.getValue().length();
        return this.min > length || length > max;
    }
}
