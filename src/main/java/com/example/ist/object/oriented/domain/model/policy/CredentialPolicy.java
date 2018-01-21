package com.example.ist.object.oriented.domain.model.policy;

import com.example.ist.object.oriented.domain.exception.ViolatedCredentialPolicyException;
import com.example.ist.object.oriented.domain.model.identity.AuthenticationFactor;

public abstract class CredentialPolicy<T extends AuthenticationFactor> {
    protected String policyName = this.getClass().getSimpleName();

    public final void validate(T factor) {
        if(notSatisfiedBy(factor)) {
            throwViolatedCredentialPolicyException();
        }
    }

    protected abstract boolean notSatisfiedBy(T factor);

    private void throwViolatedCredentialPolicyException() {
        throw new ViolatedCredentialPolicyException("violated " + policyName());
    }

    protected String policyName() {
        return policyName;
    }
}
