package com.example.ist.object.oriented.domain.model.policy;

import com.example.ist.object.oriented.domain.model.identity.Password;

import java.util.regex.Pattern;

public class CharacterPolicy extends CredentialPolicy {
    private final Pattern pattern;

    public CharacterPolicy(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    @Override
    protected boolean notSatisfiedBy(Password password) {
        return !pattern.matcher(password.getValue()).find();
    }

}
