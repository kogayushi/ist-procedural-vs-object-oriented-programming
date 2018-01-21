package com.example.ist.object.oriented.application;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import com.example.ist.object.oriented.application.command.ChangePasswordCommand;
import com.example.ist.object.oriented.application.command.ChangeUsernameCommand;
import com.example.ist.object.oriented.domain.exception.ViolatedCredentialPolicyException;
import com.example.ist.object.oriented.domain.model.identity.Password;
import com.example.ist.object.oriented.domain.model.identity.User;
import com.example.ist.object.oriented.domain.model.identity.UserRepository;
import com.example.ist.object.oriented.domain.model.identity.Username;
import com.example.ist.object.oriented.domain.model.policy.PolicyFactory;
import com.example.ist.object.oriented.infrastructure.persistence.InMemoryUserRepository;
import com.example.ist.procedural.exception.ViolatedUsernamePolicyException;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

import java.util.UUID;


@SpringBootTest(classes = {IdentityService.class, PolicyFactory.class, InMemoryUserRepository.class})
@RunWith(Theories.class)
public class IdentityServiceTest {
    private static final UUID TEST_USER_ID = UUID.fromString("8A1E74BD-FBC9-43B2-9AAC-0D356022F887");

    private static final String SHORTER_THAN_4                      = "Ps0";
    private static final String SHORTER_THAN_8                      = "1234567";
    private static final String GREATER_THAN_20                     = "123456789012345678901";
    private static final String NOT_CONTAINS_UPPER_CASE_ALPHABET    = "loweralphabet8";
    private static final String NOT_CONTAINS_LOWER_CASE_ALPHABET    = "UPPERALPHABET8";
    private static final String NOT_CONTAINS_NUMBER                 = "alphabetONLY";
    private static final String CONTAINS_INVALID_CHARACTER          = "Passw0rdぱすわーど";
    private static final String SAME_WITH_CURRENT_USERNAME          = "Yushi.Koga.314";
    private static final String SAME_WITH_CURRENT_PASSWORD          = "Passw0rd";
    private static final String CONTAINS_FIRST_NAME                 = "Yushi0314";
    private static final String CONTAINS_LAST_NAME                  = "Koga0314";
    private static final String SAME_WITH_MAIL_ADDRESS              = "K314@is-tech.co.jp";
    private static final String CONTAINS_TELEPHONE_NUMBER           = "09012345678Password";

    @DataPoints("policySatisfiedUsernames")
    public static String[] POLICY_SATISFIED_USERNAME_FIXTURE = {"Pas0", "Hogeh0ge", "WhiteDay314"};

    @DataPoints("policySatisfiedPasswords")
    public static String[] POLICY_SATISFIED_PASSWORD_FIXTURE = {"Passw00rd", "Hogeh0ge", "WhiteDay314"};

    @DataPoints("policyViolatedUsernames")
    public static String[] POLICY_VIOLATED_USERNAME_FIXTURE = { SHORTER_THAN_4,
                                                                GREATER_THAN_20,
                                                                NOT_CONTAINS_UPPER_CASE_ALPHABET,
                                                                NOT_CONTAINS_LOWER_CASE_ALPHABET,
                                                                NOT_CONTAINS_NUMBER,
                                                                CONTAINS_INVALID_CHARACTER,
                                                                SAME_WITH_CURRENT_USERNAME,
                                                                SAME_WITH_CURRENT_PASSWORD,
                                                                CONTAINS_FIRST_NAME,
                                                                CONTAINS_LAST_NAME,
                                                                SAME_WITH_MAIL_ADDRESS,
                                                                CONTAINS_TELEPHONE_NUMBER};


    @DataPoints("policyViolatedPasswords")
    public static String[] POLICY_VIOLATED_PASSWORD_FIXTURE = { SHORTER_THAN_8,
                                                                GREATER_THAN_20,
                                                                NOT_CONTAINS_UPPER_CASE_ALPHABET,
                                                                NOT_CONTAINS_LOWER_CASE_ALPHABET,
                                                                NOT_CONTAINS_NUMBER,
                                                                CONTAINS_INVALID_CHARACTER,
                                                                SAME_WITH_CURRENT_USERNAME,
                                                                SAME_WITH_CURRENT_PASSWORD,
                                                                CONTAINS_FIRST_NAME,
                                                                CONTAINS_LAST_NAME,
                                                                SAME_WITH_MAIL_ADDRESS,
                                                                CONTAINS_TELEPHONE_NUMBER};

    @ClassRule
    public static final SpringClassRule springClassRule = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Autowired
    IdentityService sut;

    @Autowired
    UserRepository userRepository;

    @Before
    public void setUp() {
        userRepository.updateUsername(TEST_USER_ID, new Username("Yushi.Koga.314"));
        userRepository.updatePassword(TEST_USER_ID, new Password("Passw0rd"));
    }

    @Theory
    public void testChangeUsernameWithPolicySatisfiedUsername(@FromDataPoints("policySatisfiedUsernames") String fixture) throws Exception {
        try {

            // exercise
            sut.changeUsername(new ChangeUsernameCommand(TEST_USER_ID, fixture));

        } finally {
            // verify
            User actual = userRepository.userFromId(TEST_USER_ID);

            assertThat(actual.getCredentials().getUsername().getValue(), is(fixture));
        }
    }

    @Theory
    public void testChangeUsernameWithPolicyViolatedUsername(@FromDataPoints("policyViolatedUsernames") String fixture) throws Exception {
        // set up
        thrown.expect(ViolatedCredentialPolicyException.class);

        try {

            // exercise
            sut.changeUsername(new ChangeUsernameCommand(TEST_USER_ID, fixture));

        } finally {
            // verify
            User actual = userRepository.userFromId(TEST_USER_ID);

            if (!SAME_WITH_CURRENT_PASSWORD.equals(fixture)) {
                assertThat(actual.getCredentials().getUsername(), is(not(fixture)));
            }
            assertThat(actual.getCredentials().getUsername().getValue(), is(SAME_WITH_CURRENT_USERNAME));
        }
    }

    @Theory
    public void testChangeUsernameWithPolicySatisfiedPassword(@FromDataPoints("policySatisfiedPasswords") String fixture) throws Exception {
        try {

            // exercise
            sut.changePassword(new ChangePasswordCommand(TEST_USER_ID, fixture));

        } finally {
            // verify
            User actual = userRepository.userFromId(TEST_USER_ID);

            assertThat(actual.getCredentials().getPassword().getValue(), is(fixture));
        }
    }

    @Theory
    public void testChangePasswordWithPolicyViolatedPassword(@FromDataPoints("policyViolatedPasswords") String fixture) throws Exception {
        // set up
        thrown.expect(ViolatedCredentialPolicyException.class);

        try {

            // exercise
            sut.changePassword(new ChangePasswordCommand(TEST_USER_ID, fixture));

        } finally {
            // verify
            User actual = userRepository.userFromId(TEST_USER_ID);

            if (!SAME_WITH_CURRENT_PASSWORD.equals(fixture)) {
                assertThat(actual.getCredentials().getPassword(), is(not(fixture)));
            }
            assertThat(actual.getCredentials().getPassword().getValue(), is(SAME_WITH_CURRENT_PASSWORD));
        }
    }

}