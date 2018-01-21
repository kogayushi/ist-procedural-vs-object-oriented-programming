package com.example.ist.object.oriented.domain.model.policy;

import com.example.ist.object.oriented.domain.model.identity.AuthenticationFactor;

public class NotContainsNamePolicy extends Policy {
    private final String firstName;
    private final String lastName;

    public NotContainsNamePolicy(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    protected boolean notSatisfiedBy(AuthenticationFactor factor) {
        return factor.getValue().toUpperCase().contains(this.firstName.toUpperCase())
                || factor.getValue().toUpperCase().contains(this.lastName.toUpperCase());
    }
}
