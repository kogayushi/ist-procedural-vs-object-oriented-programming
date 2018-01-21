package com.example.ist.object.oriented.infrastructure.persistence;

import com.example.ist.object.oriented.domain.model.identity.ContactInformation;
import com.example.ist.object.oriented.domain.model.identity.MailAddress;
import com.example.ist.object.oriented.domain.model.identity.Password;
import com.example.ist.object.oriented.domain.model.identity.Person;
import com.example.ist.object.oriented.domain.model.identity.Sex;
import com.example.ist.object.oriented.domain.model.identity.TelephoneNumber;
import com.example.ist.object.oriented.domain.model.identity.User;
import com.example.ist.object.oriented.domain.model.identity.Credentials;
import com.example.ist.object.oriented.domain.model.identity.UserRepository;
import com.example.ist.object.oriented.domain.model.identity.Username;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Component
public class InMemoryUserRepository implements UserRepository {

    private Map<UUID, User> inMemory = initialize();

    private Map<UUID, User> initialize() {
        // @formatter:off
        Map<UUID, User> users = new HashMap<>();
        users.put(
                UUID.fromString("8A1E74BD-FBC9-43B2-9AAC-0D356022F887"),
                new User(
                        UUID.fromString("8A1E74BD-FBC9-43B2-9AAC-0D356022F887"),
                        new Credentials(
                                new Username("Yushi.Koga.314"),
                                new Password("Passw0rd")),
                        new Person(
                                "yushi",
                                "koga",
                                Sex.MALE,
                                new ContactInformation(
                                        new TelephoneNumber("090-1234-5678"),
                                        new MailAddress("K314@is-tech.co.jp")))));
        // @formatter:on

        return users;
    }

    @Override
    public User userFromId(UUID id) {
        return this.inMemory.get(id);
    }

    @Override
    public void updatePassword(UUID id, Password password) {
        User user = userFromId(id);
        user.changePasswordWith(password);
        this.inMemory.put(id, user);
        log.info("{}'s password has been updated with {}", id , password);
    }

    @Override
    public void updateUsername(UUID id, Username username) {
        User user = userFromId(id);
        user.changeUsernameWith(username);
        this.inMemory.put(id, user);
    }

}
