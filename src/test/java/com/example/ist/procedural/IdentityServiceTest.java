package com.example.ist.procedural;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import com.example.ist.procedural.dao.UserDao;
import com.example.ist.procedural.entity.User;
import com.example.ist.procedural.exception.ViolatedPasswordPolicyException;
import com.example.ist.procedural.service.IdentityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;


@SpringBootTest(classes = {IdentityService.class, UserDao.class})
@RunWith(Theories.class)
public class IdentityServiceTest {
    private static final String TEST_USER_ID = "8A1E74BD-FBC9-43B2-9AAC-0D356022F887";

    private static final String SHORTER_THAN_8                      = "1234567";
    private static final String GREATER_THAN_20                     = "123456789012345678901";
    private static final String NOT_INCLUDING_UPPER_CASE_ALPHABET   = "loweralphabet8";
    private static final String NOT_INCLUDING_LOWER_CASE_ALPHABET   = "UPPERALPHABET8";
    private static final String NOT_INCLUDING_NUMBER                = "alphabetONLY";
    private static final String INCLUDING_INVALID_CHARACTER         = "Passw0rdぱすわーど";
    private static final String SAME_WITH_USERNAME                  = "Yushi.Koga.314";
    private static final String SAME_WITH_CURRENT_PASSWORD          = "Passw0rd";
    private static final String INCLUDING_FIRST_NAME                = "Yushi314";
    private static final String INCLUDING_LAST_NAME                 = "Koga314";
    private static final String SAME_WITH_MAIL_ADDRESS              = "K314@is-tech.co.jp";
    private static final String SAME_WITH_TELEPHONE_NUMBER          = "090-1234-5678";

    @DataPoints("policySatisfiedPasswords")
    public static String[] POLICY_SATISFIED_PASSWORD_FIXTURE = {"Passw00rd", "Hogeh0ge", "WhiteDay314"};

    @DataPoints("policyViolatedPasswords")
    public static String[] POLICY_VIOLATED_PASSWORD_FIXTURE = { SHORTER_THAN_8,
                                                                GREATER_THAN_20,
                                                                NOT_INCLUDING_UPPER_CASE_ALPHABET,
                                                                NOT_INCLUDING_LOWER_CASE_ALPHABET,
                                                                NOT_INCLUDING_NUMBER,
                                                                INCLUDING_INVALID_CHARACTER,
                                                                SAME_WITH_USERNAME,
                                                                SAME_WITH_CURRENT_PASSWORD,
                                                                INCLUDING_FIRST_NAME,
                                                                INCLUDING_LAST_NAME,
                                                                SAME_WITH_MAIL_ADDRESS,
                                                                SAME_WITH_TELEPHONE_NUMBER };

    @ClassRule
    public static final SpringClassRule springClassRule = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Rule
    public final ExpectedException thrown = ExpectedException.none();
    @Autowired
    IdentityService sut;

    @Autowired
    UserDao userDao;

    @Before
    public void setUp() {
        userDao.updatePassword(TEST_USER_ID, "Passw0rd");
    }

    @Theory
    public void testChangePasswordWithPolicySatisfiedPassword(@FromDataPoints("policySatisfiedPasswords") String fixture) throws Exception {

        try {

            // exercise
            sut.changePassword(TEST_USER_ID, fixture);

        } finally {
            // verify
            User actual = userDao.selectBy(TEST_USER_ID);

            assertThat(actual.getPassword(), is(fixture));
        }
    }

    @Theory
    public void testChangePasswordWithPolicyViolatedPassword(@FromDataPoints("policyViolatedPasswords") String fixture) throws Exception {
        // set up
        thrown.expect(ViolatedPasswordPolicyException.class);
        User user = this.userDao.selectBy(TEST_USER_ID);

        try {

            // exercise
            sut.changePassword(TEST_USER_ID, fixture);

        } finally {
            // verify
            User actual = userDao.selectBy(TEST_USER_ID);

            if (!SAME_WITH_CURRENT_PASSWORD.equals(fixture)) {
                assertThat(actual.getPassword(), is(not(fixture)));
            }
            assertThat(actual.getPassword(), is(user.getPassword()));
        }
    }

}