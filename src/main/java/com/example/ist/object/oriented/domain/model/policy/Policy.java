package com.example.ist.object.oriented.domain.model.policy;

import com.example.ist.object.oriented.domain.exception.ViolatedPasswordPolicyException;
import com.example.ist.object.oriented.domain.model.identity.AuthenticationFactor;

public abstract class Policy {
    protected String policyName = this.getClass().getSimpleName();

    public final void validate(AuthenticationFactor factor) {
        if(notSatisfiedBy(factor)) {
            throwViolatedPasswordPolicyException();
        }
    }

    protected abstract boolean notSatisfiedBy(AuthenticationFactor factor);

    private void throwViolatedPasswordPolicyException() {
        throw new ViolatedPasswordPolicyException("violated " + policyName());
    }

    protected String policyName() {
        return policyName;
    }
}
