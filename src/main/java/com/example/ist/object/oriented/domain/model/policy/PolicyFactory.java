package com.example.ist.object.oriented.domain.model.policy;

import com.example.ist.object.oriented.domain.model.identity.ContactInformation;
import com.example.ist.object.oriented.domain.model.identity.Person;
import com.example.ist.object.oriented.domain.model.identity.User;

import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

@Component
public class PolicyFactory {

    public UsernamePolicy generateUsernamePolicyFor(User user) {
        Set<CredentialPolicy> policies = commonPolicies(user);

        policies.add(new LengthPolicy(4, 20));

        return new UsernamePolicy(policies);
    }

    public PasswordPolicy generatePasswordPolicyFor(User user) {
        Set<CredentialPolicy> policies = commonPolicies(user);

        policies.add(new LengthPolicy(8, 20));

        return new PasswordPolicy(policies);
    }

    private Set<CredentialPolicy> commonPolicies(User user) {
        Set<CredentialPolicy> policies = new LinkedHashSet<>();

        // Compositeパターン refer -> https://ja.wikipedia.org/wiki/Composite_%E3%83%91%E3%82%BF%E3%83%BC%E3%83%B3
        policies.add(new CharacterCombinationPolicy());

        policies.add(new NotSameWithCurrentUsernamePolicy(user.getCredentials().getUsername()));
        policies.add(new NotSameWithCurrentPasswordPolicy(user.getCredentials().getPassword()));

        Person person = user.getPerson();

        policies.add(new NotContainsNamePolicy(person.getFirstName(), person.getLastName()));

        ContactInformation contactInformation = person.getContactInformation();
        policies.add(new NotSameWithMailAddressPolicy(contactInformation.getMailAddress()));
        policies.add(new NotContainsTelephoneNumberPolicy(contactInformation.getTelephoneNumber()));
        return policies;
    }

}
