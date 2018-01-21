package com.example.ist.object.oriented.domain.model.policy;

import com.example.ist.object.oriented.domain.model.identity.Password;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class CharacterCombinationPolicy extends CredentialPolicy {
    private static final String INCLUDING_UPPER_CASE_ALPHABET_AT_LEAST_ONE  = "^.*[A-Z].*$";
    private static final String INCLUDING_LOWER_CASE_ALPHABET_AT_LEAST_ONE  = "^.*[a-z].*$";
    private static final String INCLUDING_NUMBER_AT_LEAST_ONE               = "^.*[0-9].*$";
    private static final String NOT_INCLUDING_ALLOWED_CHARACTER             = "^(?!(.*[^a-zA-Z0-9!\"#$%&'()*+,-./:;<=>?@\\[\\\\\\]^_`{|}~]+.*)).*$";

    private final Set<CredentialPolicy> policies;

    public CharacterCombinationPolicy() {
        Set<CredentialPolicy> composedRegex = new LinkedHashSet<>();
        // Composed Regexパターン(のつもり) refer -> https://martinfowler.com/bliki/ComposedRegex.html
        composedRegex.add(new CharacterPolicy(INCLUDING_UPPER_CASE_ALPHABET_AT_LEAST_ONE));
        composedRegex.add(new CharacterPolicy(INCLUDING_LOWER_CASE_ALPHABET_AT_LEAST_ONE));
        composedRegex.add(new CharacterPolicy(INCLUDING_NUMBER_AT_LEAST_ONE));
        composedRegex.add(new CharacterPolicy(NOT_INCLUDING_ALLOWED_CHARACTER));
        this.policies = Collections.unmodifiableSet(composedRegex);
    }

    @Override
    protected boolean notSatisfiedBy(Password password) {
        for (CredentialPolicy policy : policies) {
            if (policy.notSatisfiedBy(password)) {
                return true;
            }
        }
        return false;
    }
}
