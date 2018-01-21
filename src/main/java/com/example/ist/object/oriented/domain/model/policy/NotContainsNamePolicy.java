package com.example.ist.object.oriented.domain.model.policy;

import com.example.ist.object.oriented.domain.model.identity.Password;

public class NotContainsNamePolicy extends CredentialPolicy {
    private final String firstName;
    private final String lastName;

    public NotContainsNamePolicy(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    protected boolean notSatisfiedBy(Password password) {
        return password.getValue().toUpperCase().contains(this.firstName.toUpperCase())
                || password.getValue().toUpperCase().contains(this.lastName.toUpperCase());
    }
}
