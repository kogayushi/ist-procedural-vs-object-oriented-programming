package com.example.ist.object.oriented.domain.model.policy;

import com.example.ist.object.oriented.domain.model.identity.Password;
import com.example.ist.object.oriented.domain.model.identity.TelephoneNumber;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NotContainsTelephoneNumberPolicy extends CredentialPolicy {
    private final TelephoneNumber telephoneNumber;

    @Override
    public boolean notSatisfiedBy(Password password) {
        return password.getValue().contains(this.telephoneNumber.getValue().replaceAll("-", ""));
    }

}
