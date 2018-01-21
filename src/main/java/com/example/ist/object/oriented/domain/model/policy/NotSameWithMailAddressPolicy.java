package com.example.ist.object.oriented.domain.model.policy;

import com.example.ist.object.oriented.domain.model.identity.AuthenticationFactor;
import com.example.ist.object.oriented.domain.model.identity.MailAddress;
import com.example.ist.object.oriented.domain.model.identity.Password;

public class NotSameWithMailAddressPolicy extends CredentialPolicy<AuthenticationFactor> {
    private final MailAddress mailAddress;

    public NotSameWithMailAddressPolicy(MailAddress mailAddress) {
        this.mailAddress = mailAddress;
    }

    @Override
    protected boolean notSatisfiedBy(AuthenticationFactor factor) {
        return this.mailAddress.getValue().equals(factor.getValue());
    }
}
