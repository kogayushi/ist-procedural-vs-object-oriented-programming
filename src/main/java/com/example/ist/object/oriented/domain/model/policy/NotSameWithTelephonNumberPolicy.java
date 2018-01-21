package com.example.ist.object.oriented.domain.model.policy;

import com.example.ist.object.oriented.domain.model.identity.AuthenticationFactor;
import com.example.ist.object.oriented.domain.model.identity.TelephoneNumber;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NotSameWithTelephonNumberPolicy extends Policy {
    private final TelephoneNumber telephoneNumber;

    @Override
    public boolean notSatisfiedBy(AuthenticationFactor factor) {
        return this.telephoneNumber.getValue().equals(factor.getValue());
    }

}
