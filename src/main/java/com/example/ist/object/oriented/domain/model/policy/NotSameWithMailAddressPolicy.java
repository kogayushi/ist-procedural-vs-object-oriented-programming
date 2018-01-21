package com.example.ist.object.oriented.domain.model.policy;

import com.example.ist.object.oriented.domain.model.identity.MailAddress;
import com.example.ist.object.oriented.domain.model.identity.Password;

public class NotSameWithMailAddressPolicy extends CredentialPolicy {
    private final MailAddress mailAddress;

    public NotSameWithMailAddressPolicy(MailAddress mailAddress) {
        this.mailAddress = mailAddress;
    }

    @Override
    protected boolean notSatisfiedBy(Password password) {
        return this.mailAddress.getValue().equals(password.getValue());
    }
}
