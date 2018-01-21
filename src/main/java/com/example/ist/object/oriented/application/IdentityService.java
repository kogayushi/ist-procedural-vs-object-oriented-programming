package com.example.ist.object.oriented.application;

import com.example.ist.object.oriented.application.command.ChangePasswordCommand;
import com.example.ist.object.oriented.application.command.ChangeUsernameCommand;
import com.example.ist.object.oriented.domain.model.identity.Password;
import com.example.ist.object.oriented.domain.model.identity.User;
import com.example.ist.object.oriented.domain.model.identity.UserRepository;
import com.example.ist.object.oriented.domain.model.identity.Username;
import com.example.ist.object.oriented.domain.model.policy.PasswordPolicy;
import com.example.ist.object.oriented.domain.model.policy.PolicyFactory;
import com.example.ist.object.oriented.domain.model.policy.UsernamePolicy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class IdentityService {
    private final UserRepository userRepository;
    private final PolicyFactory policyFactory;

    public void changeUsername(ChangeUsernameCommand command) {

        UUID id = command.getId();
        User user = this.userRepository.userFromId(id);

        UsernamePolicy policy = this.policyFactory.generateUsernamePolicyFor(user);
        Username username = new Username(command.getNewUsername());

        policy.validate(username);

        userRepository.updateUsername(id, username);
    }

    public void changePassword(ChangePasswordCommand command) {

        UUID id = command.getId();
        User user = this.userRepository.userFromId(id);

        PasswordPolicy policy = this.policyFactory.generatePasswordPolicyFor(user);
        Password password = new Password(command.getNewPassword());

        policy.validate(password);

        userRepository.updatePassword(id, password);
    }

}