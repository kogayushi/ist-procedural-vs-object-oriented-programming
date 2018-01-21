package com.example.ist.object.oriented.domain.model.policy;

import com.example.ist.object.oriented.domain.exception.ViolatedPasswordPolicyException;
import com.example.ist.object.oriented.domain.model.identity.Password;

public abstract class CredentialPolicy {
    protected String policyName = this.getClass().getSimpleName();

    public final void validate(Password password) {
        if(notSatisfiedBy(password)) {
            throwViolatedPasswordPolicyException();
        }
    }

    protected abstract boolean notSatisfiedBy(Password password);

    private void throwViolatedPasswordPolicyException() {
        throw new ViolatedPasswordPolicyException("violated " + policyName());
    }

    protected String policyName() {
        return policyName;
    }
}
