package com.example.ist.procedural.service;

import com.example.ist.procedural.dao.UserDao;
import com.example.ist.procedural.entity.User;
import com.example.ist.procedural.exception.ViolatedPasswordPolicyException;
import com.example.ist.procedural.exception.ViolatedUsernamePolicyException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Slf4j
@RequiredArgsConstructor
@Service
public class IdentityService {
    private static final Pattern INCLUDING_UPPER_CASE_ALPHABET_AT_LEAST_ONE = Pattern.compile("^.*[A-Z].*$");
    private static final Pattern INCLUDING_LOWER_CASE_ALPHABET_AT_LEAST_ONE = Pattern.compile("^.*[a-z].*$");
    private static final Pattern INCLUDING_NUMBER_AT_LEAST_ONE              = Pattern.compile("^.*[0-9].*$");
    private static final Pattern NOT_INCLUDING_ALLOWED_CHARACTER            = Pattern.compile("^(?!(.*[^a-zA-Z0-9!\"#$%&'()*+,-./:;<=>?@\\[\\\\\\]^_`{|}~]+.*)).*$");
    private static final String INPUTTED_USERNAME_VIOLATED_CHARACTER_POLICY = "inputted username violated character policy";
    private static final String INPUTTED_PAZZWORD_VIOLATED_CHARACTER_POLICY = "inputted password violated character policy";

    private final UserDao userDao;

    public void changeUsername(String id, String newUsername) {
        User user = this.userDao.selectBy(id);

        if (newUsername.length() < 4 || newUsername.length() > 20) {
            String msg = "inputted username violated username length policy";
            log.warn(msg); // it's warn just for testing.
            throw new ViolatedUsernamePolicyException(msg);
        }

        if (!INCLUDING_UPPER_CASE_ALPHABET_AT_LEAST_ONE.matcher(newUsername).find()) {
            String msg = INPUTTED_USERNAME_VIOLATED_CHARACTER_POLICY;
            log.warn(msg);
            throw new ViolatedUsernamePolicyException(msg);
        }

        if (!INCLUDING_LOWER_CASE_ALPHABET_AT_LEAST_ONE.matcher(newUsername).find()) {
            String msg = INPUTTED_USERNAME_VIOLATED_CHARACTER_POLICY;
            log.warn(msg);
            throw new ViolatedUsernamePolicyException(msg);
        }

        if (!INCLUDING_NUMBER_AT_LEAST_ONE.matcher(newUsername).find()) {
            String msg = INPUTTED_USERNAME_VIOLATED_CHARACTER_POLICY;
            log.warn(msg);
            throw new ViolatedUsernamePolicyException(msg);
        }

        if (!NOT_INCLUDING_ALLOWED_CHARACTER.matcher(newUsername).find()) {
            String msg = INPUTTED_USERNAME_VIOLATED_CHARACTER_POLICY;
            log.warn(msg);
            throw new ViolatedUsernamePolicyException(msg);
        }

        if (user.getUsername().equalsIgnoreCase(newUsername)) {
            String msg = "inputted username violated not same with current username policy";
            log.warn(msg);
            throw new ViolatedUsernamePolicyException(msg);
        }

        if (user.getPassword().equalsIgnoreCase(newUsername)) {
            String msg = "inputted username violated not same with current password policy";
            log.warn(msg);
            throw new ViolatedUsernamePolicyException(msg);
        }

        if (newUsername.toUpperCase().contains(user.getFirstName().toUpperCase())) {
            String msg = "inputted username violated not including first name policy";
            log.warn(msg);
            throw new ViolatedUsernamePolicyException(msg);
        }

        if (newUsername.toUpperCase().contains(user.getLastName().toUpperCase())) {
            String msg = "inputted username violated not including last name policy";
            log.warn(msg);
            throw new ViolatedUsernamePolicyException(msg);
        }

        if (newUsername.contains((user.getTelephoneNumber().replace("-", "")))) {
            String msg = "inputted username violated not contains telephone number policy";
            log.warn(msg);
            throw new ViolatedUsernamePolicyException(msg);
        }

        userDao.updateUsername(user.getId(), newUsername);

    }

    public void changePassword(String id, String newPassword) {
        User user = this.userDao.selectBy(id);

        if (newPassword.length() < 8 || newPassword.length() > 20) {
            String msg = "inputted password violated password length policy";
            log.warn(msg); // it's warn just for testing.
            throw new ViolatedPasswordPolicyException(msg);
        }

        if (!INCLUDING_UPPER_CASE_ALPHABET_AT_LEAST_ONE.matcher(newPassword).find()) {
            String msg = INPUTTED_PAZZWORD_VIOLATED_CHARACTER_POLICY;
            log.warn(msg);
            throw new ViolatedPasswordPolicyException(msg);
        }

        if (!INCLUDING_LOWER_CASE_ALPHABET_AT_LEAST_ONE.matcher(newPassword).find()) {
            String msg = INPUTTED_PAZZWORD_VIOLATED_CHARACTER_POLICY;
            log.warn(msg);
            throw new ViolatedPasswordPolicyException(msg);
        }

        if (!INCLUDING_NUMBER_AT_LEAST_ONE.matcher(newPassword).find()) {
            String msg = INPUTTED_PAZZWORD_VIOLATED_CHARACTER_POLICY;
            log.warn(msg);
            throw new ViolatedPasswordPolicyException(msg);
        }

        if (!NOT_INCLUDING_ALLOWED_CHARACTER.matcher(newPassword).find()) {
            String msg = INPUTTED_PAZZWORD_VIOLATED_CHARACTER_POLICY;
            log.warn(msg);
            throw new ViolatedPasswordPolicyException(msg);
        }

        if (user.getUsername().equalsIgnoreCase(newPassword)) {
            String msg = "inputted password violated not same with current username policy";
            log.warn(msg);
            throw new ViolatedPasswordPolicyException(msg);
        }

        if (user.getPassword().equalsIgnoreCase(newPassword)) { // Usually, stored password is hashed. But because this is just sample, it's not.
            String msg = "inputted password violated not same with current password policy";
            log.warn(msg);
            throw new ViolatedPasswordPolicyException(msg);
        }

        if (newPassword.toUpperCase().contains(user.getFirstName().toUpperCase())) {
            String msg = "inputted password violated not including first name policy";
            log.warn(msg);
            throw new ViolatedPasswordPolicyException(msg);
        }

        if (newPassword.toUpperCase().contains(user.getLastName().toUpperCase())) {
            String msg = "inputted password violated not including last name policy";
            log.warn(msg);
            throw new ViolatedPasswordPolicyException(msg);
        }

        if (newPassword.equals(user.getMailAddress())) {
            String msg = "inputted password violated not same with mail address policy";
            log.warn(msg);
            throw new ViolatedPasswordPolicyException(msg);
        }

        if (newPassword.contains((user.getTelephoneNumber().replace("-", "")))) {
            String msg = "inputted password violated not contains telephone number policy";
            log.warn(msg);
            throw new ViolatedPasswordPolicyException(msg);
        }

        userDao.updatePassword(id, newPassword);

    }

}
