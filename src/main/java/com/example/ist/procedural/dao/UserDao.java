package com.example.ist.procedural.dao;

import com.example.ist.procedural.entity.User;
import com.example.ist.procedural.enums.Sex;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class UserDao  {

    private Map<String, User> inMemory = initialize();

    private Map<String, User> initialize() {
        // @formatter:off
        Map<String, User> users = new HashMap<>();
        users.put(
                "8A1E74BD-FBC9-43B2-9AAC-0D356022F887" ,
                new User(
                        "8A1E74BD-FBC9-43B2-9AAC-0D356022F887",
                        "Yushi.Koga.314",
                        "Passw0rd",
                        "yushi",
                        "koga",
                        Sex.MALE,
                        "090-1234-5678",
                        "K314@is-tech.co.jp"));
        // @formatter:on
        return users;
    }

    public User selectBy(String id) {
        return this.inMemory.get(id.toUpperCase());
    }

    public void updateUsername(String id, String username) {
        User user = this.inMemory.get(id.toUpperCase());
        user.setUsername(username);
        this.inMemory.put(id.toUpperCase(), user);
        log.info("{}'s username has been updated with {}", id, username);

    }

    public void updatePassword(String id, String password) {
        User user = this.inMemory.get(id.toUpperCase());
        user.setPassword(password);
        this.inMemory.put(id.toUpperCase(), user);
        log.info("{}'s password has been updated with {}", id, password);
    }

}
