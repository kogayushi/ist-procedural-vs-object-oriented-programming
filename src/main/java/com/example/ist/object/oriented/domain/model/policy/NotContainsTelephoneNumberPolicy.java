package com.example.ist.object.oriented.domain.model.policy;

import com.example.ist.object.oriented.domain.model.identity.AuthenticationFactor;
import com.example.ist.object.oriented.domain.model.identity.TelephoneNumber;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NotContainsTelephoneNumberPolicy extends Policy<AuthenticationFactor> {
    private final TelephoneNumber telephoneNumber;

    @Override
    public boolean notSatisfiedBy(AuthenticationFactor factor) {
        return factor.getValue().contains(this.telephoneNumber.getValue().replaceAll("-", ""));
    }

}
