package com.example.ist.object.oriented.domain.model.policy;

import com.example.ist.object.oriented.domain.model.identity.AuthenticationFactor;

import java.util.regex.Pattern;

public class CharacterPolicy extends CredentialPolicy<AuthenticationFactor> {
    private final Pattern pattern;

    public CharacterPolicy(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    @Override
    protected boolean notSatisfiedBy(AuthenticationFactor factor) {
        return !pattern.matcher(factor.getValue()).find();
    }

}
